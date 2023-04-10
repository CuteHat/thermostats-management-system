import {Component} from '@angular/core';
import {ThermostatDto} from "../thermostat-dto.model";
import {ThermostatService} from "../thermostat.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  thermostats: ThermostatDto[] = [];

  chartOptions = {
    responsive: true,
    scales: {
      xAxes: [
        {
          type: 'time',
          time: {
            displayFormats: {
              quarter: 'MMM YYYY',
            },
          },
        },
      ],
    },
  };

  chartColors = [
    {
      borderColor: 'rgba(75,192,192,1)',
      backgroundColor: 'rgba(75,192,192,0.2)',
    },
  ];
  constructor(private thermostatService: ThermostatService) {
  }

  ngOnInit(): void {
    this.thermostatService.getThermostats().subscribe(thermostats => {
      this.thermostats = thermostats;
    });
  }

  getTemperatureData(temperatures: any[]): number[] {
    return temperatures.map((temp) => temp.temperature);
  }

  getTimestampLabels(temperatures: any[]): string[] {
    return temperatures.map((temp) => temp.timestamp);
  }
}
