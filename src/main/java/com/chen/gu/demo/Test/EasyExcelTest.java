package com.chen.gu.demo.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;

import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author wb-gc
 * @version 1.0
 * @date 2019/9/23 14:27
 */
public class EasyExcelTest {

    @Test
    public void writeExcelOneSheetOnceWrite() throws FileNotFoundException {
        //生成excel并指定输出路径
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\Themf\\withoutHeader01.xlsx");
        ExcelWriter writer = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);
        //设置SHEET
        Sheet sheet = new Sheet(1, 0);
        sheet.setSheetName("sheet01");
        //设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<>();
        titles.add(Arrays.asList("用户名ID"));
        titles.add(Arrays.asList("名称"));
        titles.add(Lists.newArrayList("年龄"));
        titles.add(Lists.newArrayList("生日"));
        table.setHead(titles);
        //查询数据导出即可，比如说一次性总共查询出100条数据
        List<List<String>> userList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            userList.add(Arrays.asList("ID_" + i, "小明" + i,String.valueOf(i),new Date().toString()));
            userList.add(Arrays.asList(100 + i + "ID_" , "小王" + i, String.valueOf(i), new Date().toString()));
        }
        writer.write0(userList,sheet,table);
        writer.finish();
    }

    /**
     * 数据量适中（100w以内）：一个sheet分批查询导出
     * 针对100w以内的记录数据可以调用该方法分批次查出然后写入到excel的一个sheet中
     * 注意：
     *      每次查询出来的数据不宜过大，根据内存大小设置合理的每次查询记录数，不会内存溢出即可
     *      数据量不能超过一个sheet存储的最大数据量105w
     */
    @Test
    public void writeExcelOneSheetMoreWrite() throws FileNotFoundException {
        //生成Excel并指定输出路径
        FileOutputStream out = new FileOutputStream("D:\\Themf\\withoutHead02.xlsx");
        ExcelWriter writer = new ExcelWriter(out,ExcelTypeEnum.XLSX);
        //设置sheet
        Sheet sheet = new Sheet(1, 0);
        sheet.setSheetName("sheet2");
        //设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<>();
        titles.add(Lists.newArrayList("用户叫什么"));
        titles.add(Lists.newArrayList("用户到底叫什么"));
        titles.add(Lists.newArrayList("他多少岁了"));
        titles.add(Lists.newArrayList("那一天出生的"));
        table.setHead(titles);
        //模拟分批查询： 总记录数50条，每次查询20条，分三次查询，最后一次查询记录数是10；
        Integer totalRowCount = 50;
        Integer pageSize = 20;
        Integer writeCount = totalRowCount % pageSize == 0 ? (totalRowCount / pageSize) : (totalRowCount / pageSize + 1);
        //此处仅仅为了模拟数据，实际环境不需要将最后一次分来，合成一个即可，参数为：currentPage = i + 1; pageSize = pageSize
        for (int i = 0; i < writeCount; i++) {
            //前两次查询，每次查询20条数据
            if (i < writeCount - 1){
                List<List<String>> userList = new ArrayList<>();
                for (int j = 0; j < pageSize; j++) {
                    userList.add(Lists.newArrayList("ID_" + Math.random(),
                        "小茗", String.valueOf(Math.random()), new Date().toString()));
                }
                writer.write0(userList,sheet,table);
            }else if (i == writeCount - 1){
                //最后一次查询 查多余的10条记录
                List<List<String>> userList = new ArrayList<>();
                Integer lastWriteRowCount = totalRowCount - (writeCount - 1) * pageSize;
                for (int j = 0; j < lastWriteRowCount; j++) {
                    userList.add(Lists.newArrayList("ID_" + Math.random()
                        , "龘琝", String.valueOf(Math.random()), new Date().toString()));
                }
                writer.write0(userList,sheet,table);
            }
        }
        writer.finish();
    }

    /**
     * 数据量很大的情况下:多个SHEET分批查询导出
     * 针对几百万的记录数可以调用该方法分多批次查出然后写入到EXCEL的多个SHEET中
     * 注意：
     *      perSheetRowCount % pageSize要能整除，为了整洁，非整除这块不做处理
     *      每次查询出来的记录数量不宜过大，根据内存大小设置合理的每次查询记录数，不会内存溢出即可
     */
    @Test
    public void writeExcelMoreSheetMoreWrite() throws FileNotFoundException {
        //生成excel并指定输出路径
        FileOutputStream out = new FileOutputStream("D:\\Themf\\withoutHead03.xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        //设置sheet名称
        String sheetName = "测试Sheet";

        //设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<>();
        titles.add(Lists.newArrayList("用户Id"));
        titles.add(Lists.newArrayList("用户名"));
        titles.add(Lists.newArrayList("用户年龄"));
        titles.add(Lists.newArrayList("用户生日"));
        table.setHead(titles);
        //模拟分批查询： 总记录数250条，每个sheet存100条，每次查询20条，则生成3个sheet，前两个sheet查询次数为5，最后一个sheet查询次数为3，最后一次写的记录是10
        // 注：该版本为了较少数据判断的复杂度，暂时perSheetRowCount要能够整除pageSize，不去做过多的处理，合理分配查询数据量大小不会内存溢出即可
        Integer totalRowCount = 2500000;
        Integer perSheetRowCount = 1000;
        Integer pageSize = 20;
        Integer sheetCount = totalRowCount % perSheetRowCount == 0
            ? (totalRowCount / perSheetRowCount) : (totalRowCount / perSheetRowCount + 1);
        Integer previousSheetWriteCount = perSheetRowCount / pageSize;
        Integer lastSheetWriteCount = totalRowCount % perSheetRowCount == 0
            ? previousSheetWriteCount
            : (totalRowCount % perSheetRowCount % pageSize == 0
                ? totalRowCount % perSheetRowCount / pageSize
                : (totalRowCount % perSheetRowCount / pageSize + 1));

        for (int i = 0; i < sheetCount; i++) {
            //创建sheet
            Sheet sheet = new Sheet(i, 0);
            sheet.setSheetName(sheetName + i);
            if (i < sheetCount - 1){
                // 前两个sheet，每个sheet查五次，每次查20条，每个sheet写满100行，2个sheet合计200行
                // 实用环境:参数:currentPage： j + 1 + previousSheetWriteCount * i, pageSize:pageSize
                for (int j = 0; j < previousSheetWriteCount; j++) {
                    List<List<String>> userList = new ArrayList<>();
                    for (int k = 0; k < 20; k++) {
                        userList.add(Lists.newArrayList("ID_" + Math.random()
                            , "小茗", String.valueOf(Math.random()),new Date().toString()));
                    }
                    writer.write0(userList, sheet, table);
                }
            }else if (i == sheetCount - 1){
                //最后一个sheet实用环境不需要将最后一次分来，合成一个即可，参数为:currentPage = i + 1 ; pageSize = pageSize
                for (int j = 0; j < lastSheetWriteCount; j++) {
                    //前两次查询，每次查询20条
                    if (j < lastSheetWriteCount - 1){
                        List<List<String>> userList = new ArrayList<>();
                        for (int k = 0; k < 20; k++) {
                            userList.add(Lists.newArrayList("ID_" + Math.random()
                                , "哒名", String.valueOf(Math.random()),new Date().toString()));
                        }
                        writer.write0(userList, sheet, table);
                    }else if (j == lastSheetWriteCount - 1){
                        //最后一次查询，将剩余的10条查询出来
                        List<List<String>> userList = new ArrayList<>();
                        Integer lastWriteRowCount = totalRowCount - (sheetCount - 1) * perSheetRowCount
                            - (lastSheetWriteCount - 1) * pageSize;
                        for (int k = 0; k < lastSheetWriteCount; k++) {
                            userList.add(Lists.newArrayList("ID_" + Math.random()
                                , "小茗252", String.valueOf(Math.random()),new Date().toString()));
                        }
                        writer.write0(userList, sheet, table);
                    }
                }
            }
        }
        writer.finish();
    }
    @Test
    public void selfTest() throws FileNotFoundException {
        FileOutputStream out = new FileOutputStream("D:\\Themf\\Self.xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(3,3);
        Table table = new Table(4);
        Table table2 = new Table(2);
        List<List<String>> titles = Lists.newArrayList("第一行", "第二行", "第三行").stream().map(Lists::newArrayList)
            .collect(Collectors.toList());
        table.setHead(titles);
        table2.setHead(titles);
        sheet.setSheetName("天下第一");
        List<List<String>> info = new ArrayList<>();
        info.add(Lists.newArrayList("111","222","333"));
        info.add(Lists.newArrayList("111","222","333"));
        info.add(Lists.newArrayList("111","222","333"));
        info.add(Lists.newArrayList("111","222","333"));
        writer.write0(info,sheet,table);
        writer.write0(info,sheet,table2);
        writer.finish();
    }
    @Test
    public void asList(){
        List<Integer> integers = Arrays.asList(1, 3, 4, 5, 67);
        //integers.add(1);
        ArrayList<Integer> integers1 = new ArrayList<>(integers);
        integers1.add(1);
        System.out.println(integers);
        Integer[] arr = {1,3,4,6,8,9};
        List<Integer> ints = Arrays.asList(arr);
        arr[3] = 1000;

        ints.set(1,200);
        System.out.println(arr);
    }
}
