/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.web.OfferFilter;

/**
 *
 * @author vivkovski
 */
@Service
@Transactional
public class OffersService {

    @PersistenceContext
    private EntityManager em;

    public CarManufacturer getCarManufacturer(int id) {
        return em.find(CarManufacturer.class, id);
    }

    public CarModel getCarModel(int id) {
        return em.find(CarModel.class, id);
    }

    public List<CarManufacturer> getCarManufacturers() {
        String jpql = "select cm from CarManufacturer cm order by cm.name";
        TypedQuery<CarManufacturer> query = em.createQuery(jpql, CarManufacturer.class);
        List<CarManufacturer> result = query.getResultList();
        return result;
    }

    public List<BodyStyle> getBodyStyles() {
        String jpql = "select bs from BodyStyle bs order by bs.name";
        TypedQuery<BodyStyle> query = em.createQuery(jpql, BodyStyle.class);
        List<BodyStyle> result = query.getResultList();
        return result;
    }

    public List<FuelType> getFuelTypes() {
        String jpql = "select ft from FuelType ft order by ft.name";
        TypedQuery<FuelType> query = em.createQuery(jpql, FuelType.class);
        List<FuelType> result = query.getResultList();
        return result;
    }

    public List<CarModel> getCarModels() {
        String jpql = "select cmo from CarModel cmo order by cmo.name";
        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        List<CarModel> result = query.getResultList();
        return result;
    }

    public List<CarModel> getCarModels(int manufacturerId) {
        String jpql = "select cm from CarModel cm where cm.manufacturer.id = :id order by cm.name";

        TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
        query.setParameter("id", manufacturerId);

        return query.getResultList();
    }

    public Offer getOffer(int id) {
        return em.find(Offer.class, id);
    }
//    public Offer getOffer(int offerId) {
//        String jpql = "select off from Offer off where off.id = :id order by off.id";
//
//        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
//        query.setParameter("id", offerId);
//
//        return query.getSingleResult();
//    }

    public List<Offer> getOffers() {
        String jpql = "select off from Offer off order by off.id";
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        List<Offer> result = query.getResultList();
        return result;
    }

    public List<Offer> getOffersByModel(int modelId) {
        String jpql = "select off from Offer off where off.model.id = :id order by off.id";

        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", modelId);

        return query.getResultList();
    }

    public List<Offer> getOffersByManufacturer(int manufacturerId) {
        String jpql = "select off from Offer off where off.model.manufacturer.id = :id order by off.id";

        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
        query.setParameter("id", manufacturerId);

        return query.getResultList();
    }

    public List<Offer> getOffers(OfferFilter filter) {

        String part1 = "select off from Offer off where 1=1";
        String part2 = " order by off.id";
        String where = "";

        System.out.println(filter.getManufacturerId());
        System.out.println(filter.getModelId());
        System.out.println(filter.getYearFrom());
        System.out.println(filter.getYearTo());
        if (filter.getManufacturerId() != null) {
            where = where + " and off.model.manufacturer.id = :manufacturer_id";
        }
        if (filter.getModelId() != null) {
            where = where + " and off.model.id = :model_id";
        }
        if (filter.getYearFrom() != null) {
            where = where + " and off.year >= :year_from";
        }
        if (filter.getYearTo() != null) {
            where = where + " and off.year <= :year_to";
        }
        if (filter.getFuelTypeId() != null) {
            where = where + " and off.fuelType.id = :fuel_type";
        }

        String jpql = part1 + where + part2;
        TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);

        if (filter.getManufacturerId() != null) {
            query.setParameter("manufacturer_id", filter.getManufacturerId());
        }
        if (filter.getModelId() != null) {
            query.setParameter("model_id", filter.getModelId());
        }
        if (filter.getYearFrom() != null) {
            query.setParameter("year_from", 1995);
        }
        if (filter.getYearTo() != null) {
            query.setParameter("year_to", filter.getYearTo());
        }
        if (filter.getFuelTypeId() != null) {
            query.setParameter("fuel_type", filter.getFuelTypeId());
        }

        return query.getResultList();

    }

    public Offer createOffer(Offer offer) {
        em.persist(offer);
        return offer;
    }

    public Offer deleteOffer(Integer id) {
        Offer offer = em.find(Offer.class, id);
        em.remove(offer);
        return offer;
    }

    public Offer saveOffer(Offer offer) {
        return em.merge(offer);
    }
}
