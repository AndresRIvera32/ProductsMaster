
import com.cosw.productsmaster.logica.ProductsMasterLogica;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class test {
    
    @Autowired
    private ProductsMasterLogica ML;
    
    
    @Test
    public void VerifPedidoTendero(){
        //boolean i=ML.VerificarPedidoTendero(20, 1);
        //System.out.println(i);
    }
}
