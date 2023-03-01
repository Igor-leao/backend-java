package com.igor.cleanartch.core.dataprovider;

import com.igor.cleanartch.core.domain.Address;

public interface FindAddressByZipCode {
    Address find(final String zipCode);
}

/*
Todos os aquivos do dataProvider são de saida do core, então as entidades são
criadas aqui e qualquer metodo tem que passar uma interface para sair do core
não é permitido uso de agentes externos como lombok*/

