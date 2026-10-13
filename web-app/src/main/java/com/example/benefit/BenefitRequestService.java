package com.example.benefit;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class BenefitRequestService {
    @PersistenceContext(unitName = "benefitPU")
    private EntityManager em;

    public List<BenefitRequest> findAll() {
        return em.createQuery("SELECT r FROM BenefitRequest r ORDER BY r.id", BenefitRequest.class).getResultList();
    }

    public void markReviewed(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("O ID da solicitação é obrigatório.");
        }

        BenefitRequest request = em.find(BenefitRequest.class, id);
        if (request == null) {
            throw new EntityNotFoundException(
                    "Solicitação de benefício não encontrada para o ID: " + id);
        }

        request.setStatus("REVIEWED");
        // A entidade está gerenciada pelo contexto de persistência; o JPA
        // sincronizará a alteração ao concluir a transação do EJB.
    }
}