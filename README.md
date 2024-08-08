# Measurements Reactive System

This README provides instructions to set up and run a Docker Compose application consisting of the following components:
- Temperature Sensor 1 (`t1_sensor`)
- Humidity Sensor 1 (`h1_sensor`)
- Measurements Warehouse (`measurements-warehouse`)
- Kafka Broker (`broker`)
- Central Service (`central-service`)
- Kafka Control Center (`control-center`)

## Components Overview

1. **Temperature Sensor 1 (`t1_sensor`)**: 
   - Simulates a temperature sensor emitting values via UDP to the warehouse.
   - Configurable parameters in `docker-compose.yml`:
     - `MAX_VALUE`: Maximum temperature value.
     - `MIN_VALUE`: Minimum temperature value.
     - `SLEEP_INTERVAL`: Time interval between samples.
     - `SENSOR_ID`: Identifier for the sensor.

2. **Humidity Sensor 1 (`h1_sensor`)**: 
   - Simulates a humidity sensor emitting values via UDP to the warehouse.
   - Configurable parameters in `docker-compose.yml`:
     - `MAX_VALUE`: Maximum humidity value.
     - `MIN_VALUE`: Minimum humidity value.
     - `SLEEP_INTERVAL`: Time interval between samples.
     - `SENSOR_ID`: Identifier for the sensor.

3. **Measurements Warehouse (`measurements-warehouse`)**: 
   - A Spring Boot service that centralizes the readings from the sensors.
   - Listens for incoming UDP packets from sensors.
   - Configured with Kafka broker details.

4. **Kafka Broker (`broker`)**: 
   - Provides asynchronous communication via Kafka.
   - Only communication between 'Measurements Warehouse' and 'central-service' involves kafka broker.

5. **Central Service (`central-service`)**: 
   - A Spring Boot service that manages information from the warehouse.
   - Logs alerts when readings exceed specified thresholds.
   - Configurable parameters in `docker-compose.yml`:
     - `THRESHOLD_TEMPERATURE`: Temperature threshold for alerts.
     - `THRESHOLD_HUMIDITY`: Humidity threshold for alerts.

6. **Kafka Control Center (`control-center`)**: 
   - Optional visual interface for Kafka.
   - Allows monitoring of messages, topics, and consumers.

## Prerequisites

- Docker

## Running the Application

1. Clone the repository containing the `docker-compose.yml` file and the necessary context folders (`sensor-simulator`, `measurements-warehouse`, `central-service`).

2. Navigate to the directory containing the `docker-compose.yml` file.

3. Build and start the Docker Compose services:
   ```bash
   docker-compose up -d


## Stop the Application

1. ```bash
   docker-compose down