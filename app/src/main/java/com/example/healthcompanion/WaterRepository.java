package com.example.healthcompanion;

import android.content.Context;

import java.util.List;

public class WaterRepository {

    private WaterDao waterDao;
    private WaterRecordsDao waterRecordsDao;

    public WaterRepository (Context context)
    {
        WaterDatabase waterDatabase = WaterDatabase.getInstance(context);
        waterDao = waterDatabase.waterDao();
        waterRecordsDao = waterDatabase.waterRecordsDao();
    }

    public void InsertWater ( Water water)
    {
        waterDao.Insert(water);
    }

    public void DeleteWater ( Water water )
    {
        waterDao.Delete(water);
    }

    public List<Water> SelectAllWater ()
    {
        return waterDao.SelectAll();
    }

    public void InsertWaterRecords ( WaterRecords waterRecords)
    {
        waterRecordsDao.Insert(waterRecords);
    }

    public void UpdateWaterRecords ( WaterRecords waterRecords )
    {
        waterRecordsDao.Update(waterRecords);
    }

    public List<WaterRecords> SelectAllWaterRecords ()
    {
        return waterRecordsDao.SelectAll();
    }

    public WaterRecords SearchWaterRecords ( String date )
    {
        return waterRecordsDao.Search(date);
    }

}
