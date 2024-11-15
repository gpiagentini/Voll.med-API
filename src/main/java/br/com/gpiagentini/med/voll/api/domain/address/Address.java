package br.com.gpiagentini.med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Address(AddressRecordData endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }

    public void updateInformation(AddressRecordData addressRecordData) {
        if(addressRecordData.logradouro() != null)
            this.logradouro = addressRecordData.logradouro();
        if(addressRecordData.bairro() != null)
            this.bairro = addressRecordData.bairro();
        if(addressRecordData.cep() != null)
            this.cep = addressRecordData.cep();
        if(addressRecordData.uf() != null)
            this.uf = addressRecordData.uf();
        if(addressRecordData.numero() != null)
            this.numero = addressRecordData.numero();
        if(addressRecordData.complemento() != null)
            this.complemento = addressRecordData.complemento();
    }
}
