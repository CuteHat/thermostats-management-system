// thermostat-dto.model.ts
export class ThermostatDto {
  id: number;
  name: string;
  location: string;
  thresholdTemperature: number;
  configuredTemperature: number;
  isCritical: boolean;
  temperatures: TemperatureDto[];

  constructor(
    id: number,
    name: string,
    location: string,
    thresholdTemperature: number,
    configuredTemperature: number,
    isCritical: boolean,
    temperatures: TemperatureDto[]
  ) {
    this.id = id;
    this.name = name;
    this.location = location;
    this.thresholdTemperature = thresholdTemperature;
    this.configuredTemperature = configuredTemperature;
    this.isCritical = isCritical;
    this.temperatures = temperatures;
  }
}

export class TemperatureDto {
  id: number;
  temperature: number;
  timestamp: string;

  constructor(id: number, temperature: number, timestamp: string) {
    this.id = id;
    this.temperature = temperature;
    this.timestamp = timestamp;
  }
}
