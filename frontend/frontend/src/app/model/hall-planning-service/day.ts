import { Cinema } from './cinema'
import { HallDay } from './hall-day';

export class Day {
  dayId: number;
  date: Date;
  cinema: Cinema;
  planning: HallDay[];
}
