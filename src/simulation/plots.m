arrivaltimes = data(:,1);
arrivaltimes = sort(table2array(arrivaltimes));
% histogram(arrivaltimes, 1000)
% title("Arrivals")
% xlabel("Arrival times in 1000 bins")
% ylabel("Number of occurances in each bin")

interarrivals = zeros(1788, 1);
for i = 1:1788
    interarrivals(i,1) = arrivaltimes(i + 1, 1) - arrivaltimes(i,1);
end

% histogram(interarrivals, 1000)
% title("Interarrival times")
% xlabel("Interarrival times in 1000 bins")
% ylabel("Number of occurances in each bin")

plot(interarrivals)
title("Interarrival times (from sorted arrivals)")
xlabel("Index")
ylabel("Interarrival time")
