# ioevent-benchmark-V1

This repository is used as the applications implemented for the following article: Orchestration ( Camunda) vs. Choreography (IOEvent) : What is the right balance for microservices?

The goal of these applications is to process a csv file data which contains a list of products and save only validated products.
The application starts by reading products from the csv file and then instantiating for each product the validation flow below. Valid products will be saved and invalid will be ignored and rejected.

The repo ioevent-file-processing is the application implemented with IOEvent and the repo process-camunda-file is the application implemented with Camunda 
# Following is the Bpmn implemented in both applications


![alt text](https://raw.githubusercontent.com/ioevent-io/ioevent-benchmark-V1/main/process-camunda-file/file-processing-exemple.jpg)

