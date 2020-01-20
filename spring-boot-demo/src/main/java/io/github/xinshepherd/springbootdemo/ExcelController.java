package io.github.xinshepherd.springbootdemo;

import io.github.xinshepherd.excel.core.base.DefaultExporter;
import io.github.xinshepherd.excel.core.base.ExporterBase;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Fuxin
 * @since 2020/1/17 17:57
 */
@Controller
@RequestMapping
public class ExcelController {
    private static final String EXCEL_VIEW = "excel-view";

    @GetMapping("/export")
    public String export(ModelMap modelMap) {
        List<Model> data = new ArrayList<>();
        Model model = new Model();
        model.setName("foo");
        model.setBirthDate(new Date());
        model.setAge(9);
        model.setTime(new Date());
        model.setExcelTime(DateUtil.convertTime("3:40:36"));
        model.setStringTime("3:40:36");
        model.setJavaTime(LocalTime.now());
        data.add(model);
        Model model2 = new Model();
        model2.setName("foo");
        model2.setBirthDate(new Date());
        model2.setAge(20);
        data.add(model2);
        data.add(model);
        modelMap.put("clazz", Model.class);
        modelMap.put("data", data);
        return EXCEL_VIEW;
    }

    @Component(EXCEL_VIEW)
    public static class ExcelView extends AbstractView {

        @SuppressWarnings("unchecked")
        @Override
        protected void renderMergedOutputModel(Map<String, Object> model,
                                               HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
            Workbook workbook = new HSSFWorkbook();
            ExporterBase exporter = new DefaultExporter(workbook);
            exporter.appendSheet((Class) model.get("clazz"), (List) model.get("data"));
            String fileName = (String)model.getOrDefault("fileName", "foo");
            String codedFileName = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            response.setHeader("content-disposition", String.format("attachment;filename=%s.xls", codedFileName));
            ServletOutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        }
    }

}
