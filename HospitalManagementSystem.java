import java.util.ArrayList;
import java.util.List;

enum Especialidad {
    CARDIOLOGIA,
    DERMATOLOGIA,
    PEDIATRIA,
    MEDICINAINTERNA,
    CIRUGIAGENERAL,
    OBSTETRICIAGINECOLOGIA,
    ORTOPEDIA,
    OFTALMOLOGIA,
    NEUROLOGIA,
    RADIOLOGIA,
    ANESTESIOLOGIA,
    EMERGENCIA,
    ENDOCRINOLOGIA,
    GASTROENTEROLOGIA,
    HEMATOLOGIA,
    ONCOLOGIA,
    NEFROLOGIA,
    REUMATOLOGIA
}

interface GestionCitas {
    void programarCita(CitaMedica cita);
    void cancelarCita(CitaMedica cita);
    void realizarCita(CitaMedica cita);
}

abstract class Persona {
    private String nombre;
    private String identificacion;
    private String direccion;

    public Persona(String nombre, String identificacion, String direccion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

}

class Paciente extends Persona {
    private ExpedienteMedico expediente;
    private List<CitaMedica> citas;

    public Paciente(String nombre, String identificacion, String direccion) {
        super(nombre, identificacion, direccion);
        this.expediente = new ExpedienteMedico(this);
        this.citas = new ArrayList<>();
    }

    public ExpedienteMedico getExpediente() {
        return expediente;
    }

    public List<CitaMedica> getCitas() {
        return citas;
    }

}

class Doctor extends Persona implements GestionCitas {
    private final Especialidad especialidad;
    private List<Paciente> pacientes;

    public Doctor(String nombre, String identificacion, String direccion, Especialidad especialidad) {
        super(nombre, identificacion, direccion);
        this.especialidad = especialidad;
        this.pacientes = new ArrayList<>();
    }

    @Override
    public void programarCita(CitaMedica cita) {
    }

    @Override
    public void cancelarCita(CitaMedica cita) {
    }

    @Override
    public void realizarCita(CitaMedica cita) {
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

}

class Enfermero extends Persona {
    private List<Doctor> doctores;
    private List<Paciente> pacientes;

    public Enfermero(String nombre, String identificacion, String direccion) {
        super(nombre, identificacion, direccion);
        this.doctores = new ArrayList<>();
        this.pacientes = new ArrayList<>();
    }

    public List<Doctor> getDoctores() {
        return doctores;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }
}
class CitaMedica {
    private String fecha;
    private String hora;
    private String motivo;
    private EstadoCita estado;
    private Paciente paciente;
    private Doctor doctor;

    public CitaMedica(String fecha, String hora, String motivo, Paciente paciente, Doctor doctor) {
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.paciente = paciente;
        this.doctor = doctor;
        this.estado = EstadoCita.PROGRAMADA;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
enum EstadoCita {
    PROGRAMADA,
    REALIZADA,
    CANCELADA
}

class ExpedienteMedico {
    private Paciente paciente;
    private List<String> historial;

    public ExpedienteMedico(Paciente paciente) {
        this.paciente = paciente;
        this.historial = new ArrayList<>();
    }

    public void agregarDiagnostico(String diagnostico) {
        historial.add(diagnostico);
    }

}

public class HospitalManagementSystem {
    public static void main(String[] args) {
        Paciente paciente1 = new Paciente("Juan", "12345", "Calle A");
        Paciente paciente2 = new Paciente("Maria", "67890", "Calle B");

        Doctor doctor1 = new Doctor("Dr. Pérez", "DOC001", "Hospital X", Especialidad.CARDIOLOGIA);
        Doctor doctor2 = new Doctor("Dr. López", "DOC002", "Hospital Y", Especialidad.DERMATOLOGIA);

        Enfermero enfermero1 = new Enfermero("Enfermero García", "ENF001", "Hospital X");
        Enfermero enfermero2 = new Enfermero("Enfermero Martínez", "ENF002", "Hospital Y");

        // Asignar pacientes a doctores y enfermeros
        doctor1.getPacientes().add(paciente1);
        doctor1.getPacientes().add(paciente2);
        doctor2.getPacientes().add(paciente2);

        enfermero1.getPacientes().add(paciente1);
        enfermero1.getPacientes().add(paciente2);
        enfermero2.getPacientes().add(paciente2);

        // Programar citas médicas
        CitaMedica cita1 = new CitaMedica("2024-05-15", "09:00", "Consulta general", paciente1, doctor1);
        CitaMedica cita2 = new CitaMedica("2024-05-16", "10:00", "Dolor en el pecho", paciente2, doctor1);
        CitaMedica cita3 = new CitaMedica("2024-05-17", "11:00", "Dermatitis", paciente2, doctor2);

    }
}
