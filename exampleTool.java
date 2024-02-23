public class UMLTool  { 
	
	public UMLTool() { }

    public void runEVL(String inputEpsilon, OutputStream outputStream, JsonObject response) throws Exception {
	
		String result = "";

		/*-------------------------------------
		 *  Import the meta model 
		 *-------------------------------------*/
        EPackage.Registry.INSTANCE.put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new UMLResourceFactoryImpl());
        Resource resource = resourceSet.createResource(URI.createURI(UMLPackage.eNS_URI));

        /*-------------------------------------
		 *  Load in the UML Model
		 *-------------------------------------*/
        resource.load(new ByteArrayInputStream(modelContents), null);
        InMemoryEmfModel modelMem = new InMemoryEmfModel(resource);
        modelMem.setName("Model");

		/*-------------------------------------
		 *  Load in the Epsilon Code
		 *-------------------------------------*/
        EvlModule module = new EvlModule();
        // takes in the evl code as string
        module.parse(evlCode);

		/*-------------------------------------
		 *  Tool Function 
		 *-------------------------------------*/	
        module.getContext().getModelRepository().addModel(modelMem);
        module.execute();


		result = result.concat(module.getContext().getUnsatisfiedConstraints().toString());  
		
		outputStream.write(result.getBytes());
	}

}