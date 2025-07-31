/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package senac.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import senac.cinema.models.Filme;

/**
 *
 * @author sirnivass
 */

public interface FilmeRepository extends JpaRepository<Filme, Long> {}