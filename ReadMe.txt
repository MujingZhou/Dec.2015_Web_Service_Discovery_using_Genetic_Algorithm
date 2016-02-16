This is the ReadMe text file for 18655-Lab12

0. Introduction: Based on the tutorial given which is about the Genetic Algorithm, I implemented the Genetic Algorithm-Based Web Service Discovery.

1. Java is used for this assignment.

2. User should import the *.jar under the jgap_3.6.3_full folder(actually, the importing of "jgap.jar" is enough).

3. About the screenshots:
a. lv1.jpg: This is the screenshot which shows the result of level 1. Both the 'and' and 'or' relationship of result are shown in this screenshot.

b. lv2_input1.jpg : This shows the result of using the "input.txt" as the input file.

c. lv2_inputAddService : Adding more services in "input2(Adding Services).txt" to show that the program can handle any number of services.

d. lv2_inputAddServiceCluster : Adding more service cluster in "input3(Adding ServiceCluster).txt" to show that the program can handle any number of service clusters


4. How to run the Project
For the lv1 project, user can import the *.jar as mentioned in step 2. Then User can simply start the application. The result at the top would be the one to implement the 'and' relationship. More specifically, the gene is based on (SC1&SC2&SC3) logic. The performance of the whole workflow will be displayed.

For the lv2 project, user can do the same thing as mentioned above. However, to show that the program can handle any number of services as mentioned in the instruction on Blackboard, user can comment out the original "input.txt" for the input file and uncomment the "input2(Adding Services).txt" and "input3(Adding ServiceClusters).txt" so that it can be seen that the program can not only handle any number of services ,but also can handle any number of service cluster.

5. Design thinkings:
The design of the performance is:
The whole performance of the workflow would be the combination(multiplication) of all the metrics. I will give each metric a "score" which measure how well it would contribute positively to the whole performance. For "Reliability" and "Availability", they have already be scaled to a 100 scale and the distribution is relatively clustered, so I simply  divide them by a factor 100. For the "Cost" metric, it can been seen that more the cost is , less the performance should be. So, it should be a negative feedback for the whole performance. Observing that the distribution of cost is relatively clustered (maximum cost is 53 and minimum is 11), I simply use the 11(minimum) to normalize this metric in a negative feedback way. As for the "Time" metric, it is also a negative feedback factor. However, it distributed sparsely(the maximum is 23 and the minimum is 1), so I classify them into different classes, so that there would not be significantly small value.

When calculating the overall performance, I multiply the metric of each service respectively and do a sum to get the overall performance.




