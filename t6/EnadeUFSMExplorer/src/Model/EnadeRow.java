package Model;

import com.opencsv.bean.CsvBindByName;

import java.util.ArrayList;
import java.util.List;

public class EnadeRow {

    @CsvBindByName
    private String curso;
    @CsvBindByName
    private String ano;
    @CsvBindByName
    private String prova;
    @CsvBindByName(column = "tipoquestao")
    private String tipoQuestao;
    @CsvBindByName(column = "idquestao")
    private String idQuestao;
    @CsvBindByName
    private String objeto;
    @CsvBindByName(column = "objetodetalhado")
    private String objetoDetalhado;
    @CsvBindByName
    private String gabarito;
    @CsvBindByName(column = "acertoscurso")
    private String acertosCurso;
    @CsvBindByName(column = "acertosregiao")
    private String acertosRegiao;
    @CsvBindByName(column = "acertosbrasil")
    private String acertosBrasil;
    @CsvBindByName(column = "acertosdif")
    private String acertosDif;
    @CsvBindByName
    private String texto;
    @CsvBindByName
    private String imagem;
    @CsvBindByName(column = "urlprova")
    private String urlProva;
    @CsvBindByName(column = "urlsintese")
    private String urlSintese;
    @CsvBindByName(column = "urlcurso")
    private String urlCurso;
    @CsvBindByName(column = "urlcrop")
    private String urlCrop;

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getProva() {
        return prova;
    }

    public void setProva(String prova) {
        this.prova = prova;
    }

    public String getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(String tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }

    public String getIdQuestao() {
        return idQuestao;
    }

    public void setIdQuestao(String idQuestao) {
        this.idQuestao = idQuestao;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getObjetoDetalhado() {
        return objetoDetalhado;
    }

    public void setObjetoDetalhado(String objetoDetalhado) {
        this.objetoDetalhado = objetoDetalhado;
    }

    public String getGabarito() {
        return gabarito;
    }

    public void setGabarito(String gabarito) {
        this.gabarito = gabarito;
    }

    public String getAcertosCurso() {
        return acertosCurso;
    }

    public void setAcertosCurso(String acertosCurso) {
        this.acertosCurso = acertosCurso;
    }

    public String getAcertosRegiao() {
        return acertosRegiao;
    }

    public void setAcertosRegiao(String acertosRegiao) {
        this.acertosRegiao = acertosRegiao;
    }

    public String getAcertosBrasil() {
        return acertosBrasil;
    }

    public void setAcertosBrasil(String acertosBrasil) {
        this.acertosBrasil = acertosBrasil;
    }

    public String getAcertosDif() {
        return acertosDif;
    }

    public void setAcertosDif(String acertosDif) {
        this.acertosDif = acertosDif;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getUrlProva() {
        return urlProva;
    }

    public void setUrlProva(String urlProva) {
        this.urlProva = urlProva;
    }

    public String getUrlSintese() {
        return urlSintese;
    }

    public void setUrlSintese(String urlSintese) {
        this.urlSintese = urlSintese;
    }

    public String getUrlCurso() {
        return urlCurso;
    }

    public void setUrlCurso(String urlCurso) {
        this.urlCurso = urlCurso;
    }

    public String getUrlCrop() {
        return urlCrop;
    }

    public void setUrlCrop(String urlCrop) {
        this.urlCrop = urlCrop;
    }

    @Override
    public String toString() {
        return "EnadeRow{" +
                "curso='" + curso + '\'' +
                ", ano='" + ano + '\'' +
                ", prova='" + prova + '\'' +
                ", tipoQuestao='" + tipoQuestao + '\'' +
                ", idQuestao='" + idQuestao + '\'' +
                ", objeto='" + objeto + '\'' +
                ", objetoDetalhado='" + objetoDetalhado + '\'' +
                ", gabarito='" + gabarito + '\'' +
                ", acertosCurso='" + acertosCurso + '\'' +
                ", acertosRegiao='" + acertosRegiao + '\'' +
                ", acertosBrasil='" + acertosBrasil + '\'' +
                ", acertosDif='" + acertosDif + '\'' +
                ", texto='" + texto + '\'' +
                ", imagem='" + imagem + '\'' +
                ", urlProva='" + urlProva + '\'' +
                ", urlSintese='" + urlSintese + '\'' +
                ", urlCurso='" + urlCurso + '\'' +
                ", urlCrop='" + urlCrop + '\'' +
                '}';
    }

    public static List<String> getFieldListPrettyNames() {
        return List.of("Ano", "Prova", "Tipo Questão", "Id Questão", "Objeto",
                "Acertos Curso", "Acertos Região", "Acertos Brasil", "Dif. (Curso-Brasil)", "Gabarito");
    }

    public static List<String> getFieldListNames() {
        return List.of("ano", "prova", "tipoQuestao", "idQuestao", "objeto",
                "acertosCurso", "acertosRegiao", "acertosBrasil", "acertosDif");
    }
}
