# Example setup for the applications in this repo

This repository contains 5 mini-applications that employ OpenTracing and Prometheus for monitoring.
To run these applications together with **Jaeger**, **Prometheus** and **Grafana** firstly install those. 
You can  install **Jaeger** (OpenTracing compliant tracing system), **Prometheus** and **Grafana** in the Kubernetes cluster.
My suggestion is to use operators to install those; the operators can be searched on _https://operatorhub.io/_ and follow the installation instructions for each of them.

The second step in the setup is to install each of the applications. Instructions are provided in the sub-folder on how to deploy
and use the example. In each of the following locations you will find application code, a Dockerfile and `setup.yaml`:
* [Event Application code, Dockerfile and Kubernetes setup](event/README.md)
* [Landmark Application code, Dockerfile and Kubernetes setup](landmark/README.md)
* [Itinerary Application code, Dockerfile and Kubernetes setup](itinerary/README.md)
* [Visitor Application code, Dockerfile and Kubernetes setup](visitor/README.md)
* [Order Application code, Dockerfile and Kubernetes setup](order/README.md)

## Install the example Grafana Dashboards

In this repo you will also find 2 Grafana Dashboards:
* [Sample-Jaeger-tracing-dashboard.json](Sample-Jaeger-tracing-dashboard.json) is capturing generic metrics for different application installations ($container variable).
  
    * __Span count distribution__ panel is based on the following query:
        ```
      sum(increase(span_seconds_count{container="$container"}[1m])) without (pod_name,instance,job,pod_template_hash,namespace,transaction,error)
        ```

    * __Average span duration over time__ panel is based on the following query:
    
      `
      avg(rate(span_seconds_sum{container="$container"}[1m])) without (pod_name,instance,job,namespace,pod_template_hash,transaction,error)
      `

    * __Service error ratio__ panel is based on the following query:
      ```
      sum(increase(span_seconds_count{error="true",span_kind="server",container="$container"}[1m])) without (pod,instance,job,namespace,endpoint,transaction,error,operation,span_kind) / sum(increase(span_seconds_count{span_kind="server",container="$container"}[1m])) without (pod,instance,job,namespace,endpoint,transaction,error,operation,span_kind)
      ```
* [Order_tracing.json](Order_tracing.json) is capturing metrics that are relevant to order functionality.

    * __Order service error ratio__ panel is following the errors reported for Order microservice and is based on the following query:
      ```
      sum(increase(span_seconds_count{error="true",span_kind="server",container="order"}[1m])) without (pod,instance,job,namespace,endpoint,transaction,error,operation,span_kind) / sum(increase(span_seconds_count{span_kind="server",container="order"}[1m])) without (pod,instance,job,namespace,endpoint,transaction,error,operation,span_kind)  
      ```

    * __Average event rate over time__ panel contains information about interactions with Event endpoint over time and is based on the following query:
      ```
      avg(rate(event_counter_total[5m]))without (instance,namespace)
      ```
    * __Refund ratio__ panel contains information about refunds and is based on the following query:
      ```
      sum(increase(span_seconds_count{container="order",operation!="GET",operation="refund"}[1m])) without (pod_name,instance,job,namespace,pod_template_hash)
      ```      
                  
