package com.mito.facturacionAppbackend.repositorio;

import com.mito.facturacionAppbackend.models.VentaDetalle;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IConsultaRepo extends IGenericRepo<VentaDetalle, Integer> {

    @Modifying
    @Query(value = "INSERT INTO exam_consultation(id_consultation, id_exam) VALUES(:idConsultation, :idExam)", nativeQuery = true)
    Integer register(@Param("idConsultation") Integer idVenta, @Param("idExam") Integer idProducto);
}
