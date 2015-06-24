# Apache Mahout Samples

This project is a small collection of experiments with Apache Mahout using Scala. My first experiment is a 
quick-and-dirty user recommendation engine using a small data set (a file containing 32 entries).

Data Set:
```
1,10,1.0
1,11,2.0
1,12,5.0
1,13,5.0
1,14,5.0
1,15,4.0
1,16,5.0
1,17,1.0
1,18,5.0
2,10,1.0
2,11,2.0
2,15,5.0
2,16,4.5
2,17,1.0
2,18,5.0
3,11,2.5
3,12,4.5
3,13,4.0
3,14,3.0
3,15,3.5
3,16,4.5
3,17,4.0
3,18,5.0
4,10,5.0
4,11,5.0
4,12,5.0
4,13,0.0
4,14,2.0
4,15,3.0
4,16,1.0
4,17,4.0
4,18,1.0
```

Expected output:
```
2015-06-23 21:13:49 INFO  FileDataModel:187 - Creating FileDataModel for file ./src/main/resources/dataset.csv
2015-06-23 21:13:50 INFO  FileDataModel:358 - Reading file info...
2015-06-23 21:13:50 INFO  FileDataModel:369 - Read lines: 32
2015-06-23 21:13:50 INFO  GenericDataModel:113 - Processed 4 users
2015-06-23 21:13:50 INFO  SampleRecommendation$:59 - recommendation: RecommendedItem[item:12, value:4.8328104]
2015-06-23 21:13:50 INFO  SampleRecommendation$:59 - recommendation: RecommendedItem[item:13, value:4.6656213]
2015-06-23 21:13:50 INFO  SampleRecommendation$:59 - recommendation: RecommendedItem[item:14, value:4.331242]
```