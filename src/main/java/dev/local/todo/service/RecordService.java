package dev.local.todo.service;

import dev.local.todo.api.ApiCode;
import dev.local.todo.api.ApiResponse;
import dev.local.todo.dao.RecordRepository;
import dev.local.todo.model.Record;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev.local.todo.util.LocalDateTimeUtil;
import java.sql.Timestamp;

import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.util.Calendar;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public ApiResponse getRecord(String username) {
        JSONObject response = new JSONObject();

        List<Record> record = recordRepository.findAll();

        if(record == null) {
            return ApiResponse.createFailure(ApiCode.User.REGISTERFAILURE);
        }

        List<List<Record>> previousMonthRecord = filterRecord(record, -2);
        List<List<Record>> lastMonthRecord = filterRecord(record, -1);
        List<List<Record>> currentMonthRecord = filterRecord(record, 0);

        response.put("previousMonthRecord", previousMonthRecord);
        response.put("lastMonthRecord", lastMonthRecord);
        response.put("currentMonthRecord", currentMonthRecord);

        return ApiResponse.createSuccess(ApiCode.User.ADDSUCCESS, response);
    }

    public List<List<Record>> filterRecord(List<Record> record, int monthCount) {
        List<List<Record>> result = new LinkedList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, monthCount); //get the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);// From 1st of the Month
        int dayNumOfMonth = LocalDateTimeUtil.getDaysByYearMonth(calendar.getTime());

        for (int i = 0; i < dayNumOfMonth; i++, calendar.add(Calendar.DATE, 1)) {
            Date d = calendar.getTime();
            Long dayStart = LocalDateTimeUtil.getStartOfDay(d);
            Long dayEnd = LocalDateTimeUtil.getEndOfDay(d);
            List<Record> list = new LinkedList<>();
            for(int j = 0; j < record.size(); j ++) {
                Long timestamp = record.get(j).getCreateTime().getTime();
                if( timestamp > dayStart && timestamp < dayEnd) {
                    list.add(record.get(j));
                }else if( timestamp > dayEnd) {
                    break;
                }
            }
            result.add(list);
        }
        return result;
    }
}
