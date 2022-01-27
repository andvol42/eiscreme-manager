package com.mtmd.eiscrememanager.mapper;

import com.mtmd.eiscrememanager.api.EiscremesorteDto;
import com.mtmd.eiscrememanager.domain.Eiscremesorte;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Mapper, um Eiscremesorten auf {@link com.mtmd.eiscrememanager.domain.Eiscremesorte}
 * und {@link com.mtmd.eiscrememanager.api.EiscremesorteDto} zu mappen
 * Wird von {@link com.mtmd.eiscrememanager.api.EiscremeResource} genutzt
 */
@Component
public class EiscremesorteMapper {

    public Eiscremesorte mapToEntity(EiscremesorteDto eiscremesorteDto) {

        if ( eiscremesorteDto == null ) {
            return null;
        }

        Eiscremesorte eiscremesorte = new Eiscremesorte(eiscremesorteDto.getId(),
                eiscremesorteDto.getName(),
                Eiscremesorte.Kategorie.fromString(eiscremesorteDto.getKategorie()),
                eiscremesorteDto.getZutaten(),
                eiscremesorteDto.getKcal(),
                eiscremesorteDto.getPreis());
        return eiscremesorte;
    }


    public EiscremesorteDto mapToDto(Eiscremesorte eiscremesorte) {
        if ( eiscremesorte == null ) {
            return null;
        }

        EiscremesorteDto eiscremesorteDto = new EiscremesorteDto();

        eiscremesorteDto.setId( eiscremesorte.getId() );
        eiscremesorteDto.setName( eiscremesorte.getName() );
        eiscremesorteDto.setKategorie( eiscremesorte.getKategorie().getBezeichnung() );
        Set<String> set = eiscremesorte.getZutaten();
        if ( set != null ) {
            eiscremesorteDto.setZutaten( new HashSet<String>( set ) );
        }
        eiscremesorteDto.setKcal( eiscremesorte.getKcal() );
        eiscremesorteDto.setPreis( eiscremesorte.getPreis() );

        return eiscremesorteDto;
    }

    public List<EiscremesorteDto> mapToDtos(List<Eiscremesorte> entities) {
        if ( entities == null ) {
            return null;
        }

        List<EiscremesorteDto> list = new ArrayList<EiscremesorteDto>( entities.size() );
        for ( Eiscremesorte eiscremesorte : entities ) {
            list.add( mapToDto(eiscremesorte) );
        }

        return list;
    }
}
