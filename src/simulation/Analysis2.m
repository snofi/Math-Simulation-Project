consumer614=rmmissing(consumer614);
consumer1422new=rmmissing(consumer1422new);
consumer226=rmmissing(consumer226);
corporate614=rmmissing(corporate614);
corporate1422=rmmissing(corporate1422);
corporate226=rmmissing(corporate226);

crit11=tinv([0.025,0.975], 1788); 
k11=sort(consumer614{:,2}-consumer614{:,1})
s11=std(k11)/sqrt(1789);
CI11=mean(k1)+crit11*s11;

crit12=tinv([0.025,0.975], 1788); 
k12=sort(consumer1422new{:,2}-consumer1422new{:,1})
s12=std(k12)/sqrt(1789);
CI12=mean(k12)+crit12*s12;

crit13=tinv([0.025,0.975], 1788); 
k13=sort(consumer226{:,2}-consumer226{:,1})
s13=std(k13)/sqrt(1789);
CI13=mean(k13)+crit13*s13;

crit21=tinv([0.025,0.975], 1788); 
k21=sort(corporate614{:,2}-corporate614{:,1})
s21=std(k21)/sqrt(1789);
CI21=mean(k21)+crit21*s21;

crit22=tinv([0.025,0.975], 1788); 
k22=sort(corporate1422{:,2}-corporate1422{:,1})
s22=std(k22)/sqrt(1789);
CI22=mean(k22)+crit22*s22;

crit23=tinv([0.025,0.975], 1788); 
k23=sort(corporate226{:,2}-corporate226{:,1})
s23=std(k23)/sqrt(1789);
CI23=mean(k23)+crit23*s23;