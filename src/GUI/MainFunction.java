/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.KhoaLuanBLL;
import DTO.ExclusiveRuleDTO;
import DTO.InclusiveRuleDTO;
import DTO.KhoaLuanDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author DLT
 */
public class MainFunction {
    
    KhoaLuanBLL bll = new KhoaLuanBLL();
    
    //Theorem 1
    public float GetProkTuple(ArrayList<KhoaLuanDTO> Sequence, int index, int j, int z) //pro of k tuples appearing in the sequence
    {
        float prokTuple = 1; //pro of k tuple in the sequence   
        if (index < 0 && j == 0)
        {
            return 1;
        }
        if (index < 0)
        {
            return 0;
        }
        KhoaLuanDTO tuple = new KhoaLuanDTO();
        tuple = Sequence.get(index);
        if (Sequence.size() == 1 && j == 1)
        {
            return tuple.getPro();
        }       
        if (index >= 0 && j == 0 && z == 0)
        {
            float prok = 0.0f; //pro of k not appearing in the Sequence
            for (int k = 0; k <= index; k++) //k=0 same j=1
            {
                prok = (1 - Sequence.get(k).getPro()); 
                prokTuple *= prok;
            }
            return prokTuple;
        }
        if (z > 0)
        {
            if (j < z)
            {
                return GetProkTuple(Sequence, index - 1, j, z - 2) * (1 - tuple.getPro());
            }
            else
            {
                return GetProkTuple(Sequence, index - 1, j - z, z - 2) * tuple.getPro() + GetProkTuple(Sequence, index - 1, j, z - 2) * (1 - tuple.getPro());
            }
        }
        prokTuple = GetProkTuple(Sequence, index - 1, j - 1, z) * tuple.getPro() + GetProkTuple(Sequence, index - 1, j, z) * (1 - tuple.getPro());
        return prokTuple;
    }
    
    //Theorem 3
//    public float GetTopkPro (ArrayList<KhoaLuanDTO> Sequence, int k, int i) //Get top-k pro of ti 
//    {
//        float proTopk = 0;
//        KhoaLuanDTO tuple = new KhoaLuanDTO();
//        tuple = GetTupleByIndex(Sequence, i);
//        int currentIndex = Sequence.indexOf(tuple);
//        float pro = tuple.getPro(); //pro of tuple
//        for(int j = 1; j <= k; j++)
//        {
//            proTopk += GetProkTuple(Sequence, currentIndex - 1, j - 1);
//        }
//        return pro * proTopk;
//    }
    
    public float GetProTopkWithGenerationRule(ArrayList<KhoaLuanDTO> Sequence, int k, int i) throws Exception
    {
        float proTopk = 0;
        ArrayList<KhoaLuanDTO> sequenceWithGenerationRule = new ArrayList<KhoaLuanDTO>();
        KhoaLuanDTO tuple = new KhoaLuanDTO();
        tuple = Sequence.get(i - 1);
        int z1 = 0; // ti in Rh
        int z2 = 0; // ti not in Rh
        for (int t = 0; t < (i - 1); t++)
        {
            ArrayList<ExclusiveRuleDTO> exclusiveRules = new ArrayList<ExclusiveRuleDTO>();
            ArrayList<InclusiveRuleDTO> inclusiveRules = new ArrayList<InclusiveRuleDTO>();
            if (Sequence.get(t).getStatus()== -1)
            {
                continue;
            }
            exclusiveRules = bll.GetExclusiveRulesByIndex(t + 1);
            inclusiveRules = bll.GetInclusiveRulesByIndex(t + 1);
            if(exclusiveRules.size() == 0 && inclusiveRules.size() == 0)
            {
                sequenceWithGenerationRule.add(Sequence.get(t));
                continue;
            }
            else
            {
                if (exclusiveRules.size() != 0)
                {
                    for (int e = 0; e < exclusiveRules.size(); e++)
                    { 
                        if (exclusiveRules.get(e).getExclusiveTuple() <= i)
                        {
                            if (t + 1 == exclusiveRules.get(e).getExclusiveTuple())
                            {
                                continue;
                            }

                            if (i != exclusiveRules.get(e).getExclusiveTuple() && i != t + 1) //ti not in RhLeft
                            {
                                float sumPro = Sequence.get(t).getPro() 
                                                + Sequence.get(exclusiveRules.get(e).getExclusiveTuple() - 1).getPro();
                                KhoaLuanDTO newTuple = new KhoaLuanDTO();
                                newTuple.setIndexOfTuple(Integer.valueOf(String.valueOf(Sequence.get(t).getIndex()) 
                                                                    + String.valueOf(exclusiveRules.get(e).getExclusiveTuple())));
                                newTuple.setIndex(sequenceWithGenerationRule.size());
                                newTuple.setPro(sumPro);
                                sequenceWithGenerationRule.add(newTuple);
                                Sequence.get(exclusiveRules.get(e).getExclusiveTuple() - 1).setStatus(-1);
                            }
                            else  //ti in RhLeft
                            {
                                Sequence.get(t).setStatus(-1);
                            }
                        }
                        else
                        {
                            sequenceWithGenerationRule.add(Sequence.get(t));
                        }                                 
                    }
                }
                if (inclusiveRules.size() != 0)
                {
                    for (int index = 0; index < inclusiveRules.size(); index++)
                    {
                        if (inclusiveRules.get(index).getInclusiveTuple() <= i)
                        {
                            if (i != inclusiveRules.get(index).getInclusiveTuple() && i != t + 1)  //ti not in R*h'
                            {
                                z2 = z2 + 2;
                                KhoaLuanDTO newTuple = new KhoaLuanDTO();
                                newTuple.setIndex(Integer.valueOf(String.valueOf(Sequence.get(t).getIndex()) 
                                                                    + String.valueOf(inclusiveRules.get(index).getInclusiveTuple())));
                                newTuple.setPro(Sequence.get(t).getPro());
                                sequenceWithGenerationRule.add(newTuple);
                                Sequence.get(inclusiveRules.get(index).getInclusiveTuple() - 1).setStatus(-1);

                            }
                            else  //ti in R*h'
                            {
                                if (t + 1 < i)
                                {
                                    z1++;
                                    Sequence.get(inclusiveRules.get(index).getInclusiveTuple() - 1).setStatus(-1);
                                }
                            }
                        }
                        else
                        {
                            sequenceWithGenerationRule.add(Sequence.get(t));
                        }  
                    }
                }
            }
        }      
        if (z2 > 0)
        {
            if (k > sequenceWithGenerationRule.size())
            {
                k = sequenceWithGenerationRule.size();
            }
            for(int j = 1; j <= k; j++)
            {
                proTopk += GetProkTuple(sequenceWithGenerationRule, sequenceWithGenerationRule.size() - 1, j - 1, z2);
            }
        }
        else
        {
            for(int j = 1; j <= k - z1; j++)
            {
                proTopk += GetProkTuple(sequenceWithGenerationRule, sequenceWithGenerationRule.size() - 1, j - 1, 0);
            }
        }
        
        return tuple.getPro() * proTopk;
    }
    
    public ArrayList<KhoaLuanDTO> GetSequenceTopkBestPro (ArrayList<KhoaLuanDTO> sequence, int k) throws Exception
    {
        ArrayList<KhoaLuanDTO> QtopSet = new ArrayList<KhoaLuanDTO>();
        ArrayList<KhoaLuanDTO> Q_pro = new ArrayList<KhoaLuanDTO>();
        ArrayList<KhoaLuanDTO> Q_score = new ArrayList<KhoaLuanDTO>();
        float bestPr = 0;
        float proTopk = 0;
        float p_prev = 0;
        for (int i = 0; i < sequence.size(); i++)
        {
            if (i + 1 <= k)
            {
                sequence.get(i).setTopk(sequence.get(i).getPro());
                Q_score.add(sequence.get(i));
                KhoaLuanDTO minTopkTuple = Collections.min(Q_score, new Comparator<KhoaLuanDTO>(){
                    public int compare (KhoaLuanDTO tuple1, KhoaLuanDTO tuple2) {
                        if (tuple1.getPro() < tuple2.getPro()) {
                            return -1;
                        } else if (tuple1.getPro() == tuple2.getPro()) {
                            return 0;
                        } else {
                            return 1;
                        }
                   }
                });
                bestPr = minTopkTuple.getTopk();
                p_prev = bestPr;
            }
            else
            {
                float pro = sequence.get(i).getPro(); //pro of tuple i
                if (pro > bestPr && pro > p_prev)
                {
                    proTopk = GetProTopkWithGenerationRule(sequence, k, i + 1);
                    if (proTopk > bestPr)
                    {
                        bestPr = proTopk;
                        KhoaLuanDTO nonDominatedTuple = sequence.get(i);
                        nonDominatedTuple.setTopk(proTopk);
                        Q_pro.add(nonDominatedTuple);
                        p_prev = pro;
                    }
                }
                else
                {
                    break;
                }
            }
        }    
        QtopSet.addAll(Q_score);
        QtopSet.addAll(Q_pro);
        return QtopSet;
    }
    
    public KhoaLuanDTO GetTupleByIndex (ArrayList<KhoaLuanDTO> sequence, int indexOfTuple)
    {
        KhoaLuanDTO tuple = new KhoaLuanDTO();
        for (int t = 0; t < sequence.size(); t++)
        {
            if (sequence.get(t).getIndex() == indexOfTuple)
            {
                 tuple = sequence.get(t);
                 break;
            }
        }
        return tuple;
    }
    
    public ArrayList<KhoaLuanDTO> GetRankedSequence(ArrayList<KhoaLuanDTO> dataList, int i) throws Exception
    {
        ArrayList<KhoaLuanDTO> rankedSequence = new ArrayList<KhoaLuanDTO>();
        if (i == -1) 
        {
            i = dataList.size();
        }
        for (int t = 1; t <= i; t++)
        {
            KhoaLuanDTO tuple = new KhoaLuanDTO();
            tuple = dataList.get(t - 1);
            tuple.setIndexOfTuple(t); 
            bll.UpdateIndexOfTuple(tuple);
            rankedSequence.add(tuple);
        }
        return rankedSequence;
    }
    
    public ArrayList<KhoaLuanDTO> GetSequencePTk (ArrayList<KhoaLuanDTO> sequence, int k, float thresholdPro) throws Exception
    {
        ArrayList<KhoaLuanDTO> QtopSet = new ArrayList<KhoaLuanDTO>();
        ArrayList<KhoaLuanDTO> Q_pro = new ArrayList<KhoaLuanDTO>();
        ArrayList<KhoaLuanDTO> Q_score = new ArrayList<KhoaLuanDTO>();
        float bestPr = 0;
        float proTopk = 0;
        float p_prev = 0;
        for (int i = 0; i < sequence.size(); i++)
        {
            if (i + 1 <= k)
            {
                sequence.get(i).setTopk(sequence.get(i).getPro());
                Q_score.add(sequence.get(i));
            }
            else
            {
                proTopk = GetProTopkWithGenerationRule(sequence, k, i + 1);
                if (proTopk >= thresholdPro)
                {
                    sequence.get(i).setTopk(proTopk);
                    Q_pro.add(sequence.get(i));
                }
                else
                {
                    break;
                }
            }
        }    
        QtopSet.addAll(Q_score);
        QtopSet.addAll(Q_pro);
        return QtopSet;
    }
}
