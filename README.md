# Introduction

Simple Springboot Kafka consumer for test the Kafka Compacted Log feature

## Flow
[Springboot Producer - Scheduler] -> [Kafka] <- [Springboot Consumer]

## What is Kafka Compacted Log

Log compaction is a mechanism to give finer-grained per-record retention, rather than the coarser-grained time-based retention. The idea is to selectively remove records where we have a more recent update with the same primary key. This way the log is guaranteed to have at least the last state for each key


## Requirements
* Docker + Docker Compose
* Java 11

## Start Environment
* Confluent Kafka

```sh
cd confluent-kafka
docker compose up -d
```

## Running the Example

### Create the topic

1. Open Confluent Control Center http://localhost:9021/
2. Create topic account-balance-topic with the custom properties below:

```properties
cleanup.policy=compact
min.cleanable.dirty.ratio=0.01
segment.ms=100
delete.retention.ms=100
```

### Start the producer (scheduler)

```sh
cd account-balance-producer
mvn spring-boot:run

```

Leave producing some messages and stop the producer

### Start the consumer

```sh
cd account-balance-consumer
mvn spring-boot:run

```

The consumer should print a reduced number of messages compared to the producer, because of the log compression configuration.
