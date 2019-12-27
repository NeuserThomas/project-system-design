import {
  Component,
  OnInit,
  ChangeDetectionStrategy,
  OnDestroy,
  ChangeDetectorRef,
  Input,
  SimpleChanges
} from '@angular/core';
import { BreakpointObserver, BreakpointState } from '@angular/cdk/layout';
import { CalendarView, CalendarEvent } from 'angular-calendar';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-calendar',
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalenderComponent implements OnInit, OnDestroy {
  view: CalendarView = CalendarView.Week;
  viewDate: Date = new Date();
  daysInWeek = 7;
  refresh: Subject<any> = new Subject();
  CalendarView = CalendarView;
  @Input() events:CalendarEvent[];

  private destroy$ = new Subject();

  constructor(
    private breakpointObserver: BreakpointObserver,
    private cd: ChangeDetectorRef,
    private modal: NgbModal
  ) {}

  ngOnInit() {
    const CALENDAR_RESPONSIVE = {
      small: {
        breakpoint: '(max-width: 576px)',
        daysInWeek: 2
      },
      medium: {
        breakpoint: '(max-width: 768px)',
        daysInWeek: 3
      },
      large: {
        breakpoint: '(max-width: 960px)',
        daysInWeek: 5
      }
    };

    this.breakpointObserver
      .observe(
        Object.values(CALENDAR_RESPONSIVE).map(({ breakpoint }) => breakpoint)
      )
      .pipe(takeUntil(this.destroy$))
      .subscribe((state: BreakpointState) => {
        const foundBreakpoint = Object.values(CALENDAR_RESPONSIVE).find(
          ({ breakpoint }) => !!state.breakpoints[breakpoint]
        );
        if (foundBreakpoint) {
          this.daysInWeek = foundBreakpoint.daysInWeek;
        } else {
          this.daysInWeek = 7;
        }
        this.cd.markForCheck();
      });
  }

  ngOnDestroy() {
    this.destroy$.next();
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['events']) {
      console.log("We got em boys!");
      console.log(this.events);
      this.refresh.next();
    }
  }
}
