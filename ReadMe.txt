I implemented the Genetic Algorithm-Based Web Service Discovery.

The whole performance of the workflow would be the combination(multiplication) of all the metrics. I will give each metric a "score" which measure how well it would contribute positively to the whole performance. For "Reliability" and "Availability", they have already be scaled to a 100 scale and the distribution is relatively clustered, so I simply  divide them by a factor 100. For the "Cost" metric, it can been seen that more the cost is , less the performance should be. So, it should be a negative feedback for the whole performance. Observing that the distribution of cost is relatively clustered (maximum cost is 53 and minimum is 11), I simply use the 11(minimum) to normalize this metric in a negative feedback way. As for the "Time" metric, it is also a negative feedback factor. However, it distributed sparsely(the maximum is 23 and the minimum is 1), so I classify them into different classes, so that there would not be significantly small value.

When calculating the overall performance, I multiply the metric of each service respectively and do a sum to get the overall performance.




