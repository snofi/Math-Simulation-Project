%% Author: Parth
%M=rhysData{:,:}/60;
%arTimes=M(:,1);
%probList=zeros(1,1000);
%arr_rt_cons=2;
%for i=1:1000
%    p=exp(-arr_rt_cons)*(power(arr_rt_cons, i)/factorial(i));
%    probList(i)=p;
%end

%histfit(arTimes);



%% Dist1
yList=zeros(1,1440);
rtSinList=zeros(1,24);
for t=1:1440
    rtSin=1.8*sin((t+15)/3.82)+2;
    %rtSinList(t)=rtSin;
    y=poisspdf(t,rtSin);
    yList(t)=y;
end
t1=1:1440;
%bar(t1,yList,1);  
    
%% Dist2
%pd=makedist('Normal','mu',1.2,'sigma',7/12);
%dist2=truncate(pd,5/12,1440);
%x2=linspace(-1,3,1000);
%figure
%plot(x2,pdf(pd,x2));
%hold on
%plot(x2,pdf(dist2,x2),'LineStyle','--');
%legend('Normal','Truncated')
%hold off

%% Dist3
yList3=zeros(1,24);
for t3=1:24
    if t3<11
        y3=poisspdf(t3,1);
        yList3(t3)=y3;
    else
        y3=poisspdf(t3,0.2);
        yList3(t3)=y3;
    end
end
t33=1:24;
bar(t33,yList3,1);

%% Dist4
%pd4=makedist('Normal','mu',3.6,'sigma',1.2);
%dist4=truncate(pd4,0.75,1440);
%x4=linspace(-1,8,1000);
%figure
%plot(x4,pdf(pd4,x4)); 
%hold on
%plot(x4,pdf(dist4,x4),'LineStyle','--');
%legend('Normal','Truncated')
%hold off
      