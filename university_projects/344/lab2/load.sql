load data
infile 'dataload.dat'
into table xyz
fields terminated by ','
(i, s, d DATE "dd-mm-yyyy")
