import { Cinema } from './cinema'
import { PlannedMovies } from './planned-movies';
import { HallDay } from './hall-day';

export class Day {
  dayId: number;
  date: Date;
  cinema: Cinema;
  planning: HallDay[];
}
