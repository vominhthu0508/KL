/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DTO.KhoaLuanDTO;
import java.util.*;

/**
 *
 * @author DLT
 */
public class SortByRoll implements Comparator<KhoaLuanDTO>
{
    // Used for sorting in descending order of score
    public int compare(KhoaLuanDTO row1, KhoaLuanDTO row2) {
        if (row1.getScore() > row2.getScore()) {
            return -1;
          } else if (row1.getScore() < row2.getScore()) {
            return 1;
          } else {
            return 0;
          }
    }
}
