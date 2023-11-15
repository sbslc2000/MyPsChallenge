package problems.programmers.kakaoblind.calculating_parking_fee;

import java.util.*;
import java.lang.Math;

class Main {

    static class Time {
        int hour;
        int minute;

        public Time(int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        public static int subtract(Time a, Time b) {
            int mSub, hSub, borrow = 0;

            if(b.minute - a.minute > 0) {
                mSub = b.minute - a.minute;
            } else {
                mSub = b.minute - a.minute + 60;
                borrow = 1;
            }

            hSub = (b.hour - borrow - a.hour) * 60;

            return mSub + hSub;
        }
    }

    static class Parking implements Comparable<Parking> {
        String carNum;
        List<Time> ins = new ArrayList<>();
        List<Time> outs = new ArrayList<>();
        int fee;

        public Parking(String carNum, Time in) {
            this.carNum = carNum;
            this.ins.add(in);
        }

        public void addOut(Time out) {
            this.outs.add(out);
        }

        public void addIn(Time in) {
            this.ins.add(in);
        }

        public void calculateFee(FeePolicy policy) {
            int accum = getAccumulatedTime();

            if(accum < policy.basicMinute) {
                fee = policy.basicFee;

            } else {
                int basicFee = policy.basicFee;
                int unitTime = (int)Math.ceil((double)(accum - policy.basicMinute)/policy.unitMinute);
                int unitFee = policy.unitFee;
                fee = basicFee + unitTime * unitFee;
            }
        }

        private int getAccumulatedTime() {

            if(ins.size() > outs.size()) {
                outs.add(new Time(23,59));
            }

            int i = ins.size() - 1;
            Time in , out;
            int subtract, sum = 0;

            while (i >= 0) {
                in = ins.get(i);
                out = outs.get(i);

                subtract = Time.subtract(in, out);
                sum += subtract;
                i--;
            }
            return sum;
        }

        @Override
        public int compareTo(Parking p) {
            return this.carNum.compareTo(p.carNum);
        }
    }

    static class FeePolicy {
        int basicMinute;
        int basicFee;
        int unitMinute;
        int unitFee;

        public FeePolicy(int basicMinute, int basicFee, int unitMinute, int unitFee) {
            this.basicMinute = basicMinute;
            this.basicFee = basicFee;
            this.unitMinute = unitMinute;
            this.unitFee = unitFee;
        }
    }

    /*================ solution ==============*/
    public int[] solution(int[] fees, String[] records) {

        //데이터를 파싱하여 각 객체를 생성
        FeePolicy policy = parseFees(fees);
        List<Parking> parkings = parseRecords(records);

        //계산
        for(Parking p : parkings) {
            p.calculateFee(policy);
        }

        //정렬
        Collections.sort(parkings);

        //Answer에 바인딩
        int[] answer = new int[parkings.size()];

        for(int i=0; i<parkings.size(); i++) {
            answer[i] = parkings.get(i).fee;
        }

        return answer;
    }

    private FeePolicy parseFees(int[] fees) {
        return new FeePolicy(fees[0], fees[1], fees[2], fees[3]);
    }

    private List<Parking> parseRecords(String[] records) {
        List<Parking> res = new ArrayList<>();

        for(int i = 0; i < records.length; i++) {
            String[] pstr = records[i].split(" ");
            Time time = toTime(pstr[0]);
            String carNum = pstr[1];
            Parking findParking = findParkingByCarNum(res, carNum);

            if(pstr[2].equals("IN")) {
                if(findParking == null) {
                    res.add(new Parking(carNum, time));
                } else {
                    findParking.addIn(time);
                }
            } else {
                findParking.addOut(time);
            }
        }
        return res;
    }

    private Parking findParkingByCarNum(List<Parking> list, String carNum) {
        for(Parking p : list) {
            if(p.carNum.equals(carNum))
                return p;
        }
        return null;
    }

    private Time toTime(String s) {
        String[] pstr = s.split(":");

        return new Time(
                Integer.parseInt(pstr[0]),
                Integer.parseInt(pstr[1])
        );
    }
}