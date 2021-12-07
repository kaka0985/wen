package com.tledu.controller;

import com.tledu.commom.AjaxResult;
import com.tledu.model.Book;
import com.tledu.model.Car;
import com.tledu.model.Order;
import com.tledu.model.User;
import com.tledu.service.IBookService;
import com.tledu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private IBookService bookService;
    @Autowired
    private IOrderService orderService;

    private List<Car> carList=new ArrayList<>();
     private  int num=0;



    @RequestMapping("toManager")
    public String toManager(){
        return "manager/manager";
    }
    @RequestMapping("toBookManager")
    public String toBookManager(Model model,Integer cpage, HttpServletRequest request, HttpServletResponse response){
        cpage = cpage == null ? 1 : cpage;
        Integer pageSize = 3;
        Integer start = (cpage - 1) * pageSize;
        Map<String,Object> map = new HashMap<>();
        map.put("start",start);
        map.put("pageSize",pageSize);
        List<Book> blist = bookService.getUserList(map);
        //求总条数
        Integer totalCount = bookService.getListCount();
        //求总页数
        Integer totalPage = (totalCount / pageSize) + (totalCount % pageSize ==0 ? 0 : 1);

        request.setAttribute("blist",blist);
        request.setAttribute("cpage",cpage);
        request.setAttribute("totalPage",totalPage);
        model.addAttribute("blist",blist);
        return "manager/book_manager";
    }
    @RequestMapping("toOrderManager")
    public String toOrderManager(){
        return "manager/order_manager";
    }
    @RequestMapping("deleteBookById")
    public  String deleteBookById(Integer id){
        bookService.deleteBookById(id);
        return "redirect:/manager/toBookManager";
    }
    @RequestMapping("toBookEdit")
    public String toBookEdit(Integer id,Model model){
        if(id !=null){
            Book book=bookService.getBookById(id);
            model.addAttribute("book",book);
            return "manager/book_edit";
        }else {
            return "manager/book_add";
        }
    }

    @RequestMapping("addBook")
    public String addBook(Book book, MultipartFile file) throws Exception {
        String filePath="D:\\idea\\生产实习2\\web\\static\\update\\"+file.getOriginalFilename();
        file.transferTo(new File(filePath));
        book.setImg_path("static\\update\\"+file.getOriginalFilename());
        bookService.addBook(book);

       return "redirect:/manager/toBookManager";
    }
    @RequestMapping("updateBook")
    public String updateBook(Book book, MultipartFile file) throws Exception {
        if(file!=null){
            String filePath="D:\\idea\\生产实习2\\web\\static\\update\\"+file.getOriginalFilename();
            file.transferTo(new File(filePath));
            book.setImg_path("static\\update\\"+file.getOriginalFilename());
        }

        bookService.updateBook(book);
        return "redirect:/manager/toBookManager";
    }
    @RequestMapping("/addCar")
    @ResponseBody
    public AjaxResult addCar(Integer id, HttpSession session ) {
        AjaxResult result = new AjaxResult();
        boolean flag = true;
        try {
            for (Car car : carList) {
                if (car.getId().equals(id)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                Book book = bookService.getBookById(id);
                Car car = new Car();
                car.setId(book.getId());
                car.setName(book.getName());
                num=num+1;
                session.setAttribute("num",num);
                session.setAttribute("name",book.getName());
                car.setPrice(book.getPrice());
                car.setSales(book.getSales());
                car.setStock(book.getStock());
                car.setCount(1);
                car.setTotalPrice(book.getPrice());
                carList.add(car);
                session.setAttribute("carlist",carList);//全局变量
                result.setSuccess(true);
                result.setMesg("添加成功");
            } else {
                result.setSuccess(false);
                result.setMesg("此商品在购物车已存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMesg(e.getMessage());
        }
        return result;
    }
    @RequestMapping("toShopCar")
    public String toShopCar(Model model,HttpSession session ){
        double totalPrice=0;//总价格
        for (Car car1:carList){
            DecimalFormat df = new DecimalFormat("0.00");
            totalPrice=totalPrice+(car1.getTotalPrice().doubleValue());
            totalPrice= Double.parseDouble(df.format(totalPrice));
        }
        session.setAttribute("total",totalPrice);
        return "cart/cart";
    }
    @RequestMapping("changeCarCount")
    public String changeCarCount(Integer id,Integer count,HttpSession session){
        for (Car car:carList){
            if(car.getId().equals(id)){
                car.setCount(count);
                System.out.println(carList);
            }
        }
        session.setAttribute("calist",carList);
        return "redirect:/manager/toShopCar";
    }
    @RequestMapping("/deleteCarById")
    public String deleteCarById(Integer id,HttpSession session){
        for(int i=0;i<carList.size();i++){
            Car car = carList.get(i);
            if(car.getId().equals(id)){
                carList.remove(i);
                num=num-1;
            }
        }
        session.setAttribute("carList",carList);
        session.setAttribute("num",num);
        return "redirect:/manager/toShopCar";
    }
    @RequestMapping("/deleteCarAll")
    public String deleteCarAll(HttpSession session){
        carList.clear();
        num=0;
        session.setAttribute("carList",carList);
        session.setAttribute("num",num);
        return "redirect:/manager/toShopCar";
    }
    @RequestMapping("/checkOut")
    public String checkOut(HttpSession session,HttpServletRequest request){
        User user = (User)session.getAttribute("loginUser");
        System.out.println(user.getId());
        String oid = System.currentTimeMillis() + "" + user.getId();
        for (Car car : carList) {
            //Integer id, String oid, Integer bid, Integer count, BigDecimal totalprice, Date createdate, Integer status

            Order order = new Order(null,oid,car.getId(),car.getCount(),car.getTotalPrice(),new Date(),0,user.getId());
            orderService.addOrder(order);
        }
        request.setAttribute("oid",oid);
        //结账后从购物车中删除图书信息
        carList = new ArrayList<>();
        session.setAttribute("carList",carList);
        return "cart/checkout";
    }
    @RequestMapping("/myOrder")
    public String myOrder(HttpSession session,Model model){
        User user = (User)session.getAttribute("loginUser");
        List<Order> orderList=orderService.getOrderById(user);
        System.out.println(orderList);
        model.addAttribute("orderlist",orderList);
        return "order/order";
    }

}
