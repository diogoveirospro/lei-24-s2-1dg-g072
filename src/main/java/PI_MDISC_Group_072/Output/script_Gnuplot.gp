set terminal png size 800,600
set output 'src/main/java/PI_MDISC_Group_072/Output/asymptotic_graph.png'
set title 'Execution Time vs Input Size'
set xlabel 'Input Size'
set ylabel 'Time (milliseconds)'
set datafile separator ';'
plot 'src/main/java/PI_MDISC_Group_072/Output/AsymptoticBehavior.csv' skip 1 using 1:2 with linespoints linewidth 3  title 'Asymptotic Behavior', \
