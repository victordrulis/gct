package br.com.drulis.gct.dominio;

import br.com.drulis.gct.core.Entidade;

/**
 * @author Victor Drulis Oliveira
 * @since 11 de mar de 2019
 * @contact victordrulis@gmail.com
 *
 */
public class Cliente extends Entidade {

    private String nome;
        private String cpfCnpj;
        private String tel;
        private String email;
        
        /**
         * @return the nome
         */
        public String getNome() {
            return nome;
        }
        
        /**
         * @param nome the nome to set
         */
        public void setNome(String nome) {
            this.nome = nome;
        }
        
        /**
         * @return the cpfCnpj
         */
        public String getCpfCnpj() {
            return cpfCnpj;
        }
        
        /**
         * @param cpfCnpj the cpfCnpj to set
         */
        public void setCpfCnpj(String cpfCnpj) {
            this.cpfCnpj = cpfCnpj;
        }
        
        /**
         * @return the tel
         */
        public String getTel() {
            return tel;
        }
        
        /**
         * @param tel the tel to set
         */
        public void setTel(String tel) {
            this.tel = tel;
        }
        
        /**
         * @return the email
         */
        public String getEmail() {
            return email;
        }
        
        /**
         * @param email the email to set
         */
        public void setEmail(String email) {
            this.email = email;
        }
}
