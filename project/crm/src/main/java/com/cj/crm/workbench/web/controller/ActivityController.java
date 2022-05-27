package com.cj.crm.workbench.web.controller;

import com.cj.crm.commons.constants.Constants;
import com.cj.crm.commons.domain.ReturnObject;
import com.cj.crm.commons.utils.DateUtil;
import com.cj.crm.commons.utils.HSSFUtil;
import com.cj.crm.commons.utils.UUIDUtil;
import com.cj.crm.settings.domain.User;
import com.cj.crm.settings.service.UserService;
import com.cj.crm.workbench.domain.Activity;
import com.cj.crm.workbench.domain.ActivityRemark;
import com.cj.crm.workbench.service.ActivityRemarkService;
import com.cj.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * 活动controller
 *
 * @author 杰瑞
 * @date 2022/05/14
 */

@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        //调用service方法查询所有用户
        List<User> userList = userService.queryAllUsers();
        //把数据保存到request域中
        request.setAttribute("userList",userList);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session){
        User user= (User) session.getAttribute(Constants.SESSION_USER);
        //封装参数
        activity.setId(UUIDUtil.getUUID());
        activity.setCreateTime(DateUtil.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层的方法，保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);
            if (ret>0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后再试...");
            }
        }catch (Exception e){
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后再试...");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityByConditionForPage.do")
    public @ResponseBody Object queryActivityByConditionForPage(String name,String owner,String startDate,String endDate,int pageNo,int pageSize){
        Map<String,Object> map=new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        //beginNo：从第几条数据开始，
        map.put("beginNo",(pageNo-1)*pageSize);
        //每页显示的条数
        map.put("pageSize",pageSize);

        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int totalRows=activityService.queryCountOfActivityByCondition(map);
        Map<String,Object> resMap=new HashMap<>();
        resMap.put("activityList",activityList);
        resMap.put("totalRows",totalRows);
        return resMap;
    }

    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    public @ResponseBody Object deleteActivityByIds(String[] id){
        ReturnObject returnObject=new ReturnObject();
        try{
            //调用service层删除方法
            int delete = activityService.deleteActivityByIds(id);
            if (delete>0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后再试...");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后再试...");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    public @ResponseBody Object queryActivityById(String id){
        return activityService.queryActivityById(id);
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    public @ResponseBody Object saveEditActivity(Activity activity,HttpSession session){
        User user= (User) session.getAttribute(Constants.SESSION_USER);
        //封装参数
        activity.setEditTime(DateUtil.formatDateTime(new Date()));
        activity.setEditBy(user.getId());
        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存修改的市场活动
            int update=activityService.saveEditActivity(activity);
            if (update>0){
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            }else {
                returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统繁忙，请稍后...");
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后...");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response) throws IOException {
        //设置响应类型，二进制excel类型
        response.setContentType("application/octet-stream;charset-utf-8");
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();

        //浏览器接收到响应信息之后，默认情况下直接显示窗口中打开响应信息；即使打不开，也会调应用程序打开；只有实在打不开，才会激活文件下载窗口
        //设置响应头信息，使浏览器接受到响应信息后，直接激活文件下载窗口,filename:默认文件名
        response.addHeader("Content-Disposition","attachment;filename=myStudentList.xls");
        //读取excel文件（inputStream）,输出到浏览器（OutputStream）
        InputStream is=new FileInputStream("D:\\apache-poi-test\\studentList.xls");
        byte[] buff=new byte[256];
        int len=0;
        while ((len=is.read(buff))!=-1){
            outputStream.write(buff,0,len);
        }
        //关闭资源
        is.close();
        outputStream.flush();
    }



    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) throws IOException {
        //调用service方法，查询所有的市场活动
        List<Activity> activityList = activityService.queryAllActivities();
        //调用工具类，然后写入excel文件
        HSSFWorkbook wb=new HSSFWorkbook();
        HSSFUtil.exportAllActivities(activityList,wb);

        //从磁盘上读写，效率底下。直接wb中的数据写入out输出流
        /*OutputStream os=new FileOutputStream("D:\\apache-poi-test\\activityList.xls");
        wb.write(os);
        os.close();
        wb.close();*/


        //把生成的文件下载到客户端
        response.setContentType("application/octet-stream;charset=utf-8");
        OutputStream out=response.getOutputStream();

        //浏览器接收到响应信息之后，默认情况下直接显示窗口中打开响应信息；即使打不开，也会调应用程序打开；只有实在打不开，才会激活文件下载窗口
        //设置响应头信息，使浏览器接受到响应信息后，直接激活文件下载窗口,filename:默认文件名
        response.addHeader("Content-Disposition","attachment;filename=activities.xls");
        /*InputStream is=new FileInputStream("D:\\apache-poi-test\\activityList.xls");
        byte[] buff=new byte[256];
        int len=0;
        while ((len=is.read(buff))!=-1){
            out.write(buff,0,len);
        }
        is.close();*/


        wb.write(out);
        wb.close();
        out.flush();
    }


    @RequestMapping("/workbench/activity/importActivity.do")
    public @ResponseBody Object importActivity(MultipartFile activityFile,HttpSession session){
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        ReturnObject returnObject=new ReturnObject();
        try {
            //把接收到的excel文件解析到磁盘目录,(效率底下)
            //获取原始文件名
//            String originalFilename = activityFile.getOriginalFilename();
//            File file = new File("D:\\apache-poi-test\\" + originalFilename);
//            activityFile.transferTo(file);
            //解析excel文件，获取文件中的数据，并且封装成activityList
//            InputStream is=new FileInputStream(file);
            InputStream is=activityFile.getInputStream();
            HSSFWorkbook wb=new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row=null;
            HSSFCell cell=null;
            Activity activity=null;
            List<Activity> activityList=new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row=sheet.getRow(i);
                activity=new Activity();
                activity.setId(UUIDUtil.getUUID());
                //默认拥有者为创建者本人
                activity.setOwner(user.getId());
                activity.setCreateTime(DateUtil.formatDateTime(new Date()));
                activity.setCreateBy(user.getId());
                //row.getLastCellNum()为最后一列的下标+1
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    cell=row.getCell(j);
                    //获取列中的数据
                    String  cellValue= HSSFUtil.getCellValueForStringUtil(cell);
                    if (j==0){
                        activity.setName(cellValue);
                    }else if (j==1){
                        activity.setStartDate(cellValue);
                    }else if (j==2){
                        activity.setEndDate(cellValue);
                    }else if (j==3){
                        activity.setCost(cellValue);
                    }else if(j==4){
                        activity.setDescription(cellValue);
                    }
                }
                activityList.add(activity);
            }
            //调用service方法保存数据
                int ret=activityService.saveActivityByList(activityList);

                returnObject.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                returnObject.setRetData(ret);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setMessage(Constants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统繁忙，请稍后再试...");
        }
        return returnObject;
    }


    @RequestMapping("/workbench/activity/detailActivity.do")
    public String detailActivity(String id,HttpServletRequest request){
        //调用service层方法查询数据
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> remarkList = activityRemarkService.queryActivityForDetailByActivityId(id);
        //将数据保存在request域中
        request.setAttribute("activity",activity);
        request.setAttribute("remarkList",remarkList);
        //请求转发
        return "workbench/activity/detail";
    }

}
