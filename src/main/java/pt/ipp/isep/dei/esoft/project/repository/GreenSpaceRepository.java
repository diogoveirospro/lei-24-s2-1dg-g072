package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AgendaEntry;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpace;
import pt.ipp.isep.dei.esoft.project.domain.externalModules.SortExternalModule;
import pt.ipp.isep.dei.esoft.project.domain.externalModules.SortSmalestToBigestByStatus;
import pt.ipp.isep.dei.esoft.project.domain.externalModules.SortSmalestToBigestSize;
import pt.ipp.isep.dei.esoft.project.repository.data.SerializableRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * Repositório de espaços verdes.
 * Armazena e gerencia uma lista de espaços verdes e fornece métodos para acessar e manipular esses espaços.
 */

public class GreenSpaceRepository extends SerializableRepository<List<GreenSpace>>  implements Serializable {
    List<GreenSpace> greenSpaceList;
    private static final String CONFIGURATION_FILENAME = "src/main/resources/config/config.properties";
    private static final String SORT_METHODS = "sort.methods";

    /**
     * Construtor da classe GreenSpaceRepository.
     * Inicializa o repositório carregando os dados salvos, se houver.
     */

    public GreenSpaceRepository() {
        super("greenSpaceRepository.ser");
        greenSpaceList = super.load();
    }

    /**
     * Retorna uma lista de espaços verdes gerenciados por um determinado gestor de espaços verdes,
     * ordenada de acordo com a opção de classificação especificada.
     *
     * @param greenSpaceManager o gestor de espaços verdes
     * @param sortingOption     a opção de classificação
     * @return uma lista de espaços verdes gerenciados pelo gestor, ordenada pela opção de classificação
     */

    public List<GreenSpace> getGreenSpaceListSorted(Collaborator greenSpaceManager, String sortingOption) {
        List<GreenSpace> greenSpacesManagedByGSM = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getGreenSpaceManager().getName().equalsIgnoreCase(greenSpaceManager.getName())) {
                greenSpacesManagedByGSM.add(greenSpace);
            }
        }

        SortExternalModule sorter;
        if (sortingOption.equalsIgnoreCase("type")) {
            sorter = new SortSmalestToBigestByStatus();
        } else if (sortingOption.equalsIgnoreCase("area")) {
            sorter = new SortSmalestToBigestSize();
        } else {
            return greenSpacesManagedByGSM;  // No sorting if the option is unrecognized
        }

        String referenceValue = sorter.getReferenceValue(sortingOption);
        sortBySortOption(referenceValue, sorter, greenSpacesManagedByGSM);
        return greenSpacesManagedByGSM;
    }

    /**
     * Retorna uma lista de espaços verdes gerenciados por um determinado gestor de espaços verdes.
     *
     * @param greenSpaceManager o gestor de espaços verdes
     * @return uma lista de espaços verdes gerenciados pelo gestor
     */

    public List<GreenSpace> getListGreenSpacesManagedByGsm(Collaborator greenSpaceManager) {
        List<GreenSpace> greenSpacesManagedByGSM = new ArrayList<>();
        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getGreenSpaceManager().equals(greenSpaceManager)) {
                greenSpacesManagedByGSM.add(greenSpace);
            }
        }
        return greenSpacesManagedByGSM;
    }

    /**
     * Ordena a lista de espaços verdes gerenciados pelo GSM de acordo com a opção de ordenação especificada.
     *
     * @param referenceValue          O valor de referência para a ordenação.
     * @param sorter                  O módulo externo de ordenação a ser utilizado.
     * @param greenSpacesManagedByGSM A lista de espaços verdes gerenciados pelo GSM a ser ordenada.
     */

    private void sortBySortOption(String referenceValue, SortExternalModule sorter, List<GreenSpace> greenSpacesManagedByGSM) {
        sorter.sortList(greenSpacesManagedByGSM);
    }

    /**
     * Retorna um espaço verde com o nome do parque especificado.
     *
     * @param parkName o nome do parque do espaço verde
     * @return o espaço verde com o nome do parque especificado, ou null se não for encontrado
     */

    public GreenSpace getGreenSpaceByParkName(String parkName) {
        for (GreenSpace greenSpace : greenSpaceList) {
            if (greenSpace.getParkName().equals(parkName)) {
                return greenSpace;
            }
        }
        return null;
    }

    /**
     * Retorna uma lista de métodos de classificação disponíveis para espaços verdes.
     *
     * @return uma lista de métodos de classificação disponíveis
     */

    public List<String> getSortMethods() {
        Properties props = getProperties();
        String sortMethodsString = props.getProperty(SORT_METHODS);
        String[] sortMethodsArray = sortMethodsString.split("\\s*,\\s*");
        return new ArrayList<>(Arrays.asList(sortMethodsArray));
    }

    /**
     * Obtém as propriedades do arquivo de configuração.
     *
     * @return as propriedades do arquivo de configuração
     */

    private Properties getProperties() {
        Properties props = new Properties();
        try {
            InputStream in = new FileInputStream(CONFIGURATION_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {
            System.out.println("Properties were not loaded successfully.");
        }
        return props;
    }

    /**
     * Adiciona um novo espaço verde ao repositório e salva os dados em arquivo.
     *
     * @param greenSpace o espaço verde a ser adicionado
     */

    public void addGreenSpace(GreenSpace greenSpace) {
        greenSpaceList.add(greenSpace);
        saveGreenSpaceRepositoryToFile();
    }

    /**
     * Salva a lista de espaços verdes no arquivo de repositório.
     */

    public void saveGreenSpaceRepositoryToFile() {
        save(greenSpaceList);
    }

    /**
     * Retorna a lista de espaços verdes armazenados no repositório.
     *
     * @return a lista de espaços verdes
     */

    public List<GreenSpace> getGreenSpaceList() {
        return greenSpaceList;
    }
}
