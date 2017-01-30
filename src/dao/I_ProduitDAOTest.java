package dao;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.AtLeast;

import application.ControleurAchatVente;
import application.ControleurCatalogue;
import application.ControleurPrincipal;
import application.ControleurStocks;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;
import metier.Produit;

public class I_ProduitDAOTest {

	private I_Catalogue catalogue;
	private I_ProduitDAO dao;
	private ControleurCatalogue controleur_catalogue;
	private ControleurAchatVente controleur_achat_vente;
	private ControleurStocks controleur_stock;

	@Before
	public void setUp() throws Exception {
		catalogue = new Catalogue();
		dao = DAOFactory.getInstance().createDAO(DAOFactory.TYPE_MOCK);
		controleur_catalogue = new ControleurCatalogue(catalogue, dao);
		controleur_achat_vente = new ControleurAchatVente(catalogue, dao);
		controleur_stock = new ControleurStocks(catalogue);
	}

	@After
	public void tearDown() throws Exception {
		// on remet à zero le catalogue
		this.catalogue = null;
		this.controleur_achat_vente = null;
		this.controleur_catalogue = null;
		this.controleur_catalogue = null;
	}

	@Test
	public void testConstructeurStocks() {
		assertNotNull("créer controleur stocks", this.controleur_stock);
	}
	
	@Test
	public void testConstructeurControleurAchatVente() {
		assertNotNull("créer controleur achat vente", this.controleur_achat_vente);
	}
	
	@Test
	public void testConstructeurControleurCatalogue() {
		assertNotNull("créer controleur catalogue", this.controleur_catalogue);
	}
	
	@Test
	public void testAddProduitDAO_OK() {
		assertTrue("ajout produit qui n'existe pas", 
				this.controleur_catalogue.addProduit("Mars", 2.35, 25));
	}
	
	@Test
	public void testAddProduitDAO_KO() {
		this.controleur_catalogue.addProduit("Mars", 2.35, 25);
		
		assertFalse("ajout produit qui n'existe pas", 
				this.controleur_catalogue.addProduit("Mars", 2.35, 25));
	}
	
	@Test
	public void testRemoveProduitDAO_OK() {
		this.catalogue.addProduit("Mars", 3.85 , 45);
		
		assertTrue("supprime produit qui existe",
				this.controleur_catalogue.removeProduit("Mars"));
	}

	@Test
	public void testRemoveProduitDAO_KO() {
		assertFalse("supprime produit qui n'existe pas",
				this.controleur_catalogue.removeProduit("Mars"));
	}

	@Test
	public void testAffichageCatalogueControleur() {
		this.catalogue.addProduit("Oreo", 1.65 , 30);
		this.catalogue.addProduit("Mars", 3.85 , 45);
		assertEquals(this.catalogue.toString(),
				this.controleur_stock.getStocksCatalogue());
	}
	
	@Test
	public void testAchatStockNul(){
		this.catalogue.addProduit("Mars", 3.85 , 45);
		assertFalse("acheter quantité nulle", this.catalogue.acheterStock("Mars", 0));
	}
	
	@Test
	public void testAchatStockNegatif(){
		this.catalogue.addProduit("Mars", 3.85 , 45);
		assertFalse("acheter quantité negative", this.catalogue.acheterStock("Mars", -1));
	}
	
	@Test
	public void testAchatStockPositif(){
		this.catalogue.addProduit("Mars", 3.85 , 45);
		assertTrue("acheter quantité positive", this.catalogue.acheterStock("Mars", 1));
	}
	
	@Test
	public void testAchatProduitNonExistant(){
		this.catalogue.addProduit("Mars", 3.85 , 45);
		assertFalse("acheter quantité positive", this.catalogue.acheterStock("Twix", 1));
	}
	
	@Test
	public void testDisconnect(){
		ControleurPrincipal ctP = Mockito.mock(ControleurPrincipal.class);
		ctP.disconnect();
		Mockito.verify(ctP, Mockito.times(1)).disconnect();
	}
	
	@Test
	public void testAddQuantiteProduitDAO_OK() {
		this.controleur_catalogue.addProduit("Mars", 2.35, 25);
		
		assertTrue("achat quantite sur produit qui existe", 
				this.controleur_achat_vente.acheterStock("Mars", 24));
	}
	
	@Test
	public void testAddQuantiteProduitDAO_KO() {
		assertFalse("achat quantite sur produit qui n'existe pas", 
				this.controleur_achat_vente.acheterStock("Twix", 19));
	}
	
	@Test
	public void testRemoveQuantiteProduitDAO_OK() {
		this.controleur_catalogue.addProduit("Mars", 2.35, 15);
		
		assertTrue("vente quantite sur produit qui existe", 
				this.controleur_achat_vente.vendreStock("Mars", 14));
	}
	
	@Test
	public void testRemoveQuantiteSuperieurAuStockProduitDAO_KO() {
		this.controleur_catalogue.addProduit("Twix", 2.35, 15);
		
		assertFalse("vente quantite supérieur au stock sur produit qui existe", 
				this.controleur_achat_vente.vendreStock("Twix", 16));
	}
}
