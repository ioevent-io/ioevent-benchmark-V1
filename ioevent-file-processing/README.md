# File Processing Sample
This repository contains File Processing applications written using IO Event. it must run on Kafka middleware technologies. You have the option of running the samples against local or Docker containerized versions of Kafka. For convenience, `docker-compose.yml` files are provided as part of each application wherever it is applicable. 

# Sample Bpmn description 

This application used to process a csv file and save its valid data , first of all we read products information from csv file then send each product to the process , after loading the product it will be passed to an exclusive gateway where it will check the product information , if all product information is valid the gateway will send the product to the save task where it will update the state and log the save of the product  , else it will send the product to reject product event where it will update the state and log the rejected products.


# Following is the Bpmn implemented using IO Event


![alt text](https://raw.githubusercontent.com/ioevent-io/ioevent-benchmark-V1/main/ioevent-file-processing/ioevent-file-processing.jpg)
