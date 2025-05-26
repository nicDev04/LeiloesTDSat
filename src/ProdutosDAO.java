
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            conn = new conectaDAO().connectDB();
            String sql = "INSERT INTO produtos(nome, valor, status) VALUES (?,?,?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.execute();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no cadastro");
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        
        try{
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        
        while(resultset.next()){
            ProdutosDTO prod = new ProdutosDTO();
            prod.setId(resultset.getInt("id"));
            prod.setNome(resultset.getString("nome"));
            prod.setValor(resultset.getInt("valor"));
            prod.setStatus(resultset.getString("status"));
            listagem.add(prod);
            
        }
        
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao Listar");
        }
        
        return listagem;
    }
    
    public void venderProduto (int id){
        
        try {
            
            conn = new conectaDAO().connectDB();
            String sql = "UPDATE produtos SET status = 'Vendido' where id = ?";
            prep = conn.prepareStatement(sql);
            prep.setInt(1, id);
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Venda cadastrada com sucesso!");

            
        } catch(Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Erro ao Vender!");
            
        } 
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        
        try{
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos where status = 'Vendido' ";
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        
        while(resultset.next()){
            ProdutosDTO prod = new ProdutosDTO();
            prod.setId(resultset.getInt("id"));
            prod.setNome(resultset.getString("nome"));
            prod.setValor(resultset.getInt("valor"));
            prod.setStatus(resultset.getString("status"));
            listagem.add(prod);
            
        }
        
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao listar!");
        }
        
        return listagem;
    }
    
}
