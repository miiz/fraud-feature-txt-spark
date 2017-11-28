package com.barclays.fraud.feature.txt.spark

import org.apache.spark.{SparkConf, SparkContext, SparkFiles}

/**
 * @author ${user.name}
 */
object SparkTxtApp {
  
  def main(args : Array[String]) {
    //Create a SparkContext to initialize Spark
    val conf = new SparkConf()
    //conf.setMaster("local");
    conf.setAppName("Word Count")
    val sc = new SparkContext(conf)

    sc.addFile("https://github.com/miiz/fraud-feature-txt-spark/raw/master/src/main/resources/shakespeare.txt")
    val filePath = SparkFiles.get("shakespeare.txt")
    // Load the text into a Spark RDD, which is a distributed representation of each line of text
    val textFile = sc.textFile(filePath)

    //word count
    val counts = textFile.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)

    counts.foreach(println)
    System.out.println("Total words: " + counts.count())
    counts.saveAsTextFile("/tmp/shakespeareWordCount"+System.nanoTime())
  }

}
