package com.example.demo.user;

import com.example.demo.charts.Chart;
import com.example.demo.charts.MonthlyChart;
import com.example.demo.charts.RedChartDecorator;
import com.example.demo.charts.WeeklyChart;
import com.example.demo.dream.Dream;
import org.jfree.chart.JFreeChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/v1/login")
@CrossOrigin()
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService= userService;
    }

    @GetMapping()
    public List<Login> getUsers(){
        return userService.getUsers();
    }

    @GetMapping(path="{userId}")
    public List<Dream> getDreams(@PathVariable("userId") Long userId){
        return userService.getDreams(userId);
    }


    @PostMapping
    public void registerNewUser(@RequestBody Login user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteStudent(@PathVariable("userId") Long userId){
        userService.deleteStudent(userId);
    }

    @PutMapping(path = "{userId}")
    public void updateStudent(@RequestBody Dream dream,
                              @PathVariable("userId")Long userId) {
        userService.updateUser(userId,dream);
    }

    @PostMapping(produces = MediaType.IMAGE_PNG_VALUE,path = "{userId}")
    public @ResponseBody byte[] createWeekOrMonthChart(@RequestBody Map<String, String> request,
                                                       @PathVariable("userId")Long userId) throws IOException, ParseException {

        // selectare intrari in functie de tag
        //Request-month,tag,time, week
        List<Dream> lista = userService.generateChartData(request.get("tag"),userId);

        JFreeChart finalChart = null;

        // datele venite din frontend pe care le pasam la Strategy Method

        String week = request.get("week");
        String month = request.get("month");

        if(request.get("time").equals("weekly")){
           //Chart weeklyChart = new RedChartDecorator(new WeeklyChart(week));

            //System.out.println("\nWeekly chart");
           // finalChart = weeklyChart.generateChart(lista);
            WeeklyChart weeklyChart = new WeeklyChart(week);
            finalChart = weeklyChart.generateChart(lista);
        }else if(request.get("time").equals("monthly")){
           // Chart monthlyChart = new RedChartDecorator(new MonthlyChart(month));

           // System.out.println("\nMonthly chart");
            //finalChart = monthlyChart.generateChart(lista);
            MonthlyChart monthlyChart = new MonthlyChart(month);
            finalChart = monthlyChart.generateChart(lista);
        }

        // Create a buffered image of the chart
        BufferedImage image = finalChart.createBufferedImage(800, 600);

        // Convert the image to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();
         
        return bytes;
    }
   /* @PostMapping
    public void registerNewDream(@RequestBody Login user, @RequestBody Dream dream){
        userService.addNewDream(dream,user);
    }*/
}
