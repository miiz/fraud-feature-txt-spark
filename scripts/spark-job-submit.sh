#!/usr/bin/env bash
spark-submit --class com.barclays.fraud.feature.txt.spark.SparkTxtApp --master local target/fraud-feature-txt-spark-1.0-SNAPSHOT.jar