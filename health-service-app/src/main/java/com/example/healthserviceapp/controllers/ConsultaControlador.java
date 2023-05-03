
package com.example.healthserviceapp.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.healthserviceapp.entity.Paciente;
import com.example.healthserviceapp.entity.Profesional;
import com.example.healthserviceapp.service.ConsultaService;
import com.example.healthserviceapp.service.ProfesionalService;
import com.example.healthserviceapp.utility.Dias;

@Controller
@RequestMapping("/consulta")
@PreAuthorize("hasAnyRole('ROLE_PACIENTE')")
public class ConsultaControlador {
    @Autowired
    private ProfesionalService profesionalService;

    @Autowired
    private ConsultaService consultaService;

    @GetMapping("/provincia")
    public String provincias(HttpSession session, ModelMap modelo) {
        modelo.put("paso", 1);
        modelo.put("provincias", profesionalService.listarProvincias());
        return "consulta.html";
    }

    @GetMapping("/especialidad")
    public String especialidades(@RequestParam String provincia, HttpSession session, ModelMap modelo) {
        modelo.put("paso", 2);
        modelo.put("provincia", provincia);
        modelo.put("especialidades", profesionalService.listarEspecialidadesPorProvincia(provincia));
        return "consulta.html";
    }

    @GetMapping("/profesional")
    public String profesionales(@RequestParam String provincia, @RequestParam String especialidad, HttpSession session,
            ModelMap modelo) {
        modelo.put("paso", 3);
        modelo.put("profesionales",
                profesionalService.listarProfesionalPorEspecialidadesPorProvincia(provincia, especialidad));
        return "consulta.html";
    }

    @GetMapping("/disponibilidad")
    public String disponibilidadProfesional(@RequestParam String idProfesional, HttpSession session, ModelMap modelo) {
        modelo.put("paso", 4);
        Profesional profesional = profesionalService.getOne(idProfesional);
        modelo.put("profesional", profesional);

        ArrayList<String> listaA = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        Dias dias = new Dias(profesional.getDisponibilidad().getDias());
        for (LocalDate date = today; date.isBefore(today.plusDays(91)); date = date.plusDays(1)) {
            if (dias.comprobar(date.getDayOfWeek())){
                listaA.add(date.format(formatter));
            }
        }
        Long nTurnos = profesional.getDisponibilidad().totalDeTurnos();
        List<String> listaB = consultaService.listarConsultasPorProfesionalAgrupadoPorFecha(profesional, nTurnos);
        for (String item : listaB) {
            listaA.remove(item);
        }
        modelo.put("fechas", listaA);
        return "consulta.html";
    }

    @GetMapping("/horario")
    public String horarioProfesional(@RequestParam String idProfesional, @RequestParam String fecha,
            HttpSession session, ModelMap modelo) {
        modelo.put("paso", 5);
        Profesional profesional = profesionalService.getOne(idProfesional);
        modelo.put("profesional", profesional);
        modelo.put("fecha", fecha);
        ArrayList<Integer> listaA = new ArrayList<>();
        Integer entrada = profesional.getDisponibilidad().getEntrada();
        Integer inicioDescanso = profesional.getDisponibilidad().getInicioDescanso();
        for (int i = entrada; i < inicioDescanso; i++) {
            listaA.add(i);
        }
        Integer finDescanso = profesional.getDisponibilidad().getFinDescanso();
        Integer salida = profesional.getDisponibilidad().getSalida();
        for (int i = finDescanso; i < salida; i++) {
            listaA.add(i);
        }
        List<Integer> listaB = consultaService.listarHorarioPorProfesionalPorFecha(profesional, fecha);
        for (Integer item : listaB) {
            listaA.remove(item);
        }
        modelo.put("horarios", listaA);
        return "consulta.html";
    }

    @GetMapping("/reservar")
    public String reservar(@RequestParam String idProfesional, @RequestParam String fecha,
            @RequestParam Integer horario, HttpSession session, ModelMap modelo) {
        Paciente paciente = (Paciente) session.getAttribute("usuariosession");
        consultaService.guardarConsulta(idProfesional, paciente.getId(), fecha, horario);
        return "redirect:/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarConsulta(@PathVariable String id) {
        consultaService.eliminar(id);
        return "redirect:/";
    }
}
