/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fontys.time;

/**
 *
 * @author daan
 */
public class TimeSpan2 implements ITimeSpan{
    
    private ITime bt;
    private long duration;
    
    /**
     * 
     * @param bt must be earlier than et
     * @param et 
     */
    public TimeSpan2(ITime bt, ITime et) {
        if (bt.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        this.bt = bt;
        duration = et.difference(bt);
        
    }

    @Override
    public ITime getBeginTime() {
        return bt;
    }

    @Override
    public ITime getEndTime() {
        ITime t = new Time((Time)bt);
        t=t.plus((int)duration);
        return t;
    }

    @Override
    public int length() {
        return (int)duration;
    }

    @Override
    public void setBeginTime(ITime beginTime) {
        ITime et = new Time((Time)bt.plus((int)duration));
         if (beginTime.compareTo(et) <= 0) {
            throw new IllegalArgumentException("begin time "
                    + bt + " must be earlier than end time " + et);
        }

        bt = beginTime;
    }

    @Override
    public void setEndTime(ITime endTime) {
        if (endTime.compareTo(bt) >= 0) {
            throw new IllegalArgumentException("end time "
                    + endTime + " must be later then begin time " + bt);
        }

        duration = bt.difference(endTime);
    }

    @Override
    public void move(int minutes) {
        bt = bt.plus(minutes);
    }

    @Override
    public void changeLengthWith(int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("length of period must be positive");
        }
        
        duration += minutes;
    }

    @Override
    public boolean isPartOf(ITimeSpan timeSpan) {
        return (getBeginTime().compareTo(timeSpan.getBeginTime()) >= 0
                && getEndTime().compareTo(timeSpan.getEndTime()) <= 0);
    }

    @Override
    public ITimeSpan unionWith(ITimeSpan timeSpan) {
        ITime et = new Time((Time)bt.plus((int)duration));
        if (bt.compareTo(timeSpan.getEndTime()) < 0 || et.compareTo(timeSpan.getBeginTime()) > 0) {
            return null;
        }
      
        ITime begintime, endtime;
        if (bt.compareTo(timeSpan.getBeginTime()) < 0) {
            begintime = bt;
        } else {
            begintime = timeSpan.getBeginTime();
        }

        if (et.compareTo(timeSpan.getEndTime()) > 0) {
            endtime = et;
        } else {
            endtime = timeSpan.getEndTime();
        }

        return new TimeSpan(begintime, endtime);
        
    }

    @Override
    public ITimeSpan intersectionWith(ITimeSpan timeSpan2) {
        ITime et = new Time((Time)bt.plus((int)duration));
       ITime begintime, endtime;
        if (bt.compareTo(timeSpan2.getBeginTime()) > 0) {
            begintime = bt;
        } else {
            begintime = timeSpan2.getBeginTime();
        }

        if (et.compareTo(timeSpan2.getEndTime()) < 0) {
            endtime = et;
        } else {
            endtime = timeSpan2.getEndTime();
        }


        return new TimeSpan(begintime, endtime);
    }
}
