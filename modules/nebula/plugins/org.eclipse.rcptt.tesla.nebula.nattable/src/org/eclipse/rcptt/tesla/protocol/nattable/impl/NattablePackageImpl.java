/**
 */
package org.eclipse.rcptt.tesla.protocol.nattable.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.rcptt.tesla.core.protocol.ProtocolPackage;

import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEvent;
import org.eclipse.rcptt.tesla.protocol.nattable.NatTableMouseEventKind;
import org.eclipse.rcptt.tesla.protocol.nattable.NattableFactory;
import org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NattablePackageImpl extends EPackageImpl implements NattablePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass natTableMouseEventEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum natTableMouseEventKindEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.rcptt.tesla.protocol.nattable.NattablePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private NattablePackageImpl() {
		super(eNS_URI, NattableFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link NattablePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static NattablePackage init() {
		if (isInited) return (NattablePackage)EPackage.Registry.INSTANCE.getEPackage(NattablePackage.eNS_URI);

		// Obtain or create and register package
		NattablePackageImpl theNattablePackage = (NattablePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof NattablePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new NattablePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		ProtocolPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theNattablePackage.createPackageContents();

		// Initialize created meta-data
		theNattablePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theNattablePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(NattablePackage.eNS_URI, theNattablePackage);
		return theNattablePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNatTableMouseEvent() {
		return natTableMouseEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNatTableMouseEvent_Row() {
		return (EAttribute)natTableMouseEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNatTableMouseEvent_Column() {
		return (EAttribute)natTableMouseEventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNatTableMouseEvent_Button() {
		return (EAttribute)natTableMouseEventEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNatTableMouseEvent_StateMask() {
		return (EAttribute)natTableMouseEventEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNatTableMouseEvent_Kind() {
		return (EAttribute)natTableMouseEventEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getNatTableMouseEventKind() {
		return natTableMouseEventKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NattableFactory getNattableFactory() {
		return (NattableFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		natTableMouseEventEClass = createEClass(NAT_TABLE_MOUSE_EVENT);
		createEAttribute(natTableMouseEventEClass, NAT_TABLE_MOUSE_EVENT__ROW);
		createEAttribute(natTableMouseEventEClass, NAT_TABLE_MOUSE_EVENT__COLUMN);
		createEAttribute(natTableMouseEventEClass, NAT_TABLE_MOUSE_EVENT__BUTTON);
		createEAttribute(natTableMouseEventEClass, NAT_TABLE_MOUSE_EVENT__STATE_MASK);
		createEAttribute(natTableMouseEventEClass, NAT_TABLE_MOUSE_EVENT__KIND);

		// Create enums
		natTableMouseEventKindEEnum = createEEnum(NAT_TABLE_MOUSE_EVENT_KIND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ProtocolPackage theProtocolPackage = (ProtocolPackage)EPackage.Registry.INSTANCE.getEPackage(ProtocolPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		natTableMouseEventEClass.getESuperTypes().add(theProtocolPackage.getElementCommand());

		// Initialize classes and features; add operations and parameters
		initEClass(natTableMouseEventEClass, NatTableMouseEvent.class, "NatTableMouseEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNatTableMouseEvent_Row(), ecorePackage.getEInt(), "row", "0", 0, 1, NatTableMouseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNatTableMouseEvent_Column(), ecorePackage.getEInt(), "column", "0", 0, 1, NatTableMouseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNatTableMouseEvent_Button(), theEcorePackage.getEInt(), "button", null, 0, 1, NatTableMouseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNatTableMouseEvent_StateMask(), theEcorePackage.getEInt(), "stateMask", null, 0, 1, NatTableMouseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNatTableMouseEvent_Kind(), this.getNatTableMouseEventKind(), "kind", "DOWN", 0, 1, NatTableMouseEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(natTableMouseEventKindEEnum, NatTableMouseEventKind.class, "NatTableMouseEventKind");
		addEEnumLiteral(natTableMouseEventKindEEnum, NatTableMouseEventKind.DOWN);
		addEEnumLiteral(natTableMouseEventKindEEnum, NatTableMouseEventKind.UP);
		addEEnumLiteral(natTableMouseEventKindEEnum, NatTableMouseEventKind.CLICK);

		// Create resource
		createResource(eNS_URI);
	}

} //NattablePackageImpl
