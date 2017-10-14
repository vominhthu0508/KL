/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.KhoaLuanDAL;
import DTO.KhoaLuanDTO;
import java.util.ArrayList;

/**
 *
 * @author DLT
 */
public class KhoaLuanBLL {
    KhoaLuanDAL dal = new KhoaLuanDAL();
    public ArrayList<KhoaLuanDTO> getAllData() throws Exception
    {
        return dal.getAllData();
    }
}
