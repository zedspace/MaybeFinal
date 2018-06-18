package claseResurse;

import java.util.Date;

public class Notare {

	private int id_notare;
	private Date data;
	private int student_numar_matricol;
	private int profesor_marca;
	private int disciplina_id_disciplina;
	private int nota;
	public int getId_notare() {
		return id_notare;
	}
	public void setId_notare(int id_notare) {
		this.id_notare = id_notare;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getStudent_numar_matricol() {
		return student_numar_matricol;
	}
	public void setStudent_numar_matricol(int student_numar_matricol) {
		this.student_numar_matricol = student_numar_matricol;
	}
	public int getProfesor_marca() {
		return profesor_marca;
	}
	public void setProfesor_marca(int profesor_marca) {
		this.profesor_marca = profesor_marca;
	}
	public int getDisciplina_id_disciplina() {
		return disciplina_id_disciplina;
	}
	public void setDisciplina_id_disciplina(int disciplina_id_disciplina) {
		this.disciplina_id_disciplina = disciplina_id_disciplina;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	
	
	
}