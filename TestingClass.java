import java.text.ParseException;
import java.util.List;

import claseResurse.AfisarePondere;
import claseResurse.AfisarePredare;
import claseResurse.AnUniversitar;
import claseResurse.Disciplina;
import claseResurse.Grupa;
import claseResurse.Pondere;
import claseResurse.Preda;
import claseResurse.Profesor;
import claseResurse.Specializare;
import database.PrelucrariDB;

public class TestingClass {

	public static void main(String[] args) throws ParseException {
		
		//profesori
		System.out.println("Toti profesorii di DB sunt:");
		List<Profesor> listaProfesori= PrelucrariDB.returnProfesor();
		//for(int i=0;i<listaProfesori.size();i++)
		//	System.out.println(listaProfesori.get(i).getMarca()+" "+listaProfesori.get(i).getTitulatura()+" "+listaProfesori.get(i).getNume()+" "+listaProfesori.get(i).getPrenume());
		for(Profesor prof:listaProfesori)
			System.out.println(prof.getMarca()+" "+prof.getTitulatura()+" "+prof.getNume()+" "+prof.getPrenume());
	
		//specializari
		System.out.println("Toate Specializarile din DB sunt :");
		List<Specializare> listaSpecializari=PrelucrariDB.returnSpecializari();
		for(Specializare specializare: listaSpecializari)
			System.out.println(specializare.getCod_specializare()+" "+specializare.getDenumire_specializare());
		
		//ani universitari
		System.out.println("Toti anii universitari din DB sunt :");
		List<Grupa> listaGrupe=PrelucrariDB.returnGrupe(1, 1);
		for(Grupa grupa: listaGrupe)
			System.out.println(grupa.getNumar_grupa()+" "+grupa.getSpecializare_cod_specializare()+" "+grupa.getAn_studiu());
		
		//disciplinele
		System.out.println("Toate disciplinele din DB sunt :");
		List<Disciplina> listaDiscipline=PrelucrariDB.returnDiscipline();
		for(Disciplina disc:listaDiscipline)
			System.out.println(disc.getDenumire_disciplina()+" "+disc.getTip_disciplina());
		
		//coduri din preda
		System.out.println("Coduri profesori care predau din DB sunt :");
		List<Preda> listaPredare=PrelucrariDB.returnPreda();
		for(Preda preda:listaPredare)
			System.out.println(preda.getProfesor_marca()+" "+preda.getDisciplina_id_disciplina()+" "+preda.getAn_universitar_id_an_universitar()+" "+preda.getGrupa_id_grupa());
	
		//nume preda
		System.out.println("Nume profesori care predau din DB sunt :");
		List<AfisarePredare> predareProfesori=PrelucrariDB.afisarePredare(listaPredare);
		for(AfisarePredare preda:predareProfesori)
			System.out.println(preda.getTitulatura()+" "+preda.getNumeProfesor()+" "+preda.getPrenumeProfesor()+" "+preda.getNumeDisciplina()+" "+preda.getTipDisciplina()+" "+preda.getSpecializare()+" "+preda.getAn()+" "+preda.getGrupa());
		
		//coduri pondere
		System.out.println("Coduri ponderi stabilite de profesori din DB sunt :");
		List<Pondere> listaPonderi=PrelucrariDB.returnPondere();
		for(Pondere pondere:listaPonderi)
			System.out.println(pondere.getProfesor_marca()+" "+pondere.getDisciplina_id_disciplina()+" "+pondere.getAn_universitar_id_an_universitar()+" "+pondere.getGrupa_id_grupa()+" "+pondere.getPondere());
		
		//nume pondere
		System.out.println("Nume ponderi stabilite de profesori din DB sunt :");
		List<AfisarePondere> ponderiStabilite=PrelucrariDB.afisarePondere(listaPonderi);
		for(AfisarePondere pondere:ponderiStabilite)
			System.out.println(pondere.getTitulatura()+" "+pondere.getNumeProfesor()+" "+pondere.getPrenumeProfesor()+" "+pondere.getNumeDisciplina()+" "+pondere.getTipDisciplina()+" "+pondere.getSpecializare()+" "+pondere.getAn()+" "+pondere.getGrupa()+" "+pondere.getPondere());
		
		//anul universitar actual
		System.out.println("Anul universitar actual:");
		AnUniversitar anActual=PrelucrariDB.returnAnActual();
		System.out.println(anActual.getId_an_universitar()+" "+anActual.getDenumire_an_universitar()+" "+anActual.getSemestrul());
		
	}

}
