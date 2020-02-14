package com.example.utils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.utils.domain.Device;
import com.example.utils.domain.DeviceService;
import com.example.utils.domain.DeviceType;
import com.example.utils.domain.Division;
import com.example.utils.domain.DivisionType;
import com.example.utils.domain.EnumValueType;
import com.example.utils.domain.Enumerated;
import com.example.utils.domain.Floor;
import com.example.utils.domain.HomeConfigEntity;
import com.example.utils.domain.House;
import com.example.utils.domain.Property;
import com.example.utils.domain.ScalarValueType;
import com.example.utils.domain.Service;
import com.example.utils.domain.User;
import com.example.utils.domain.ValueConversion;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import static com.example.utils.HouseConfigConstants.ACCESS_LEVEL;
import static com.example.utils.HouseConfigConstants.ACCESS_MODE;
import static com.example.utils.HouseConfigConstants.ADDRESS;
import static com.example.utils.HouseConfigConstants.DESCRIPTION;
import static com.example.utils.HouseConfigConstants.DEVICE;
import static com.example.utils.HouseConfigConstants.DEVICE_CLASS;
import static com.example.utils.HouseConfigConstants.DEVICE_CLASS_LIST;
import static com.example.utils.HouseConfigConstants.DEVICE_LIST;
import static com.example.utils.HouseConfigConstants.DEVICE_SERVICE;
import static com.example.utils.HouseConfigConstants.DEVICE_SERVICE_LIST;
import static com.example.utils.HouseConfigConstants.DEVICE_TYPE;
import static com.example.utils.HouseConfigConstants.DEVICE_TYPE_LIST;
import static com.example.utils.HouseConfigConstants.DIVSION;
import static com.example.utils.HouseConfigConstants.DIVSION_LIST;
import static com.example.utils.HouseConfigConstants.DIVSION_TYPE;
import static com.example.utils.HouseConfigConstants.DIVSION_TYPE_LIST;
import static com.example.utils.HouseConfigConstants.ENUMERATED;
import static com.example.utils.HouseConfigConstants.ENUM_VALUE_TYPE;
import static com.example.utils.HouseConfigConstants.ENUM_VALUE_TYPE_LIST;
import static com.example.utils.HouseConfigConstants.FLOOR;
import static com.example.utils.HouseConfigConstants.FLOOR_LIST;
import static com.example.utils.HouseConfigConstants.HEIGHT_ORDER;
import static com.example.utils.HouseConfigConstants.HOUSE;
import static com.example.utils.HouseConfigConstants.ID;
import static com.example.utils.HouseConfigConstants.MAX_VALUE;
import static com.example.utils.HouseConfigConstants.MIN_VALUE;
import static com.example.utils.HouseConfigConstants.NAME;
import static com.example.utils.HouseConfigConstants.NUM_BITS;
import static com.example.utils.HouseConfigConstants.PASSWORD;
import static com.example.utils.HouseConfigConstants.PHONE;
import static com.example.utils.HouseConfigConstants.PROPERTY;
import static com.example.utils.HouseConfigConstants.PROPERTY_LIST;
import static com.example.utils.HouseConfigConstants.REF_DEVICE_CLASS;
import static com.example.utils.HouseConfigConstants.REF_DEVICE_TYPE;
import static com.example.utils.HouseConfigConstants.REF_DIVISION;
import static com.example.utils.HouseConfigConstants.REF_DIVISION_TYPE;
import static com.example.utils.HouseConfigConstants.REF_FLOOR;
import static com.example.utils.HouseConfigConstants.REF_SERVICE;
import static com.example.utils.HouseConfigConstants.REF_VALUE_TYPE;
import static com.example.utils.HouseConfigConstants.SCALAR_VALUE_TYPE;
import static com.example.utils.HouseConfigConstants.SCALAR_VALUE_TYPE_LIST;
import static com.example.utils.HouseConfigConstants.SERVICE;
import static com.example.utils.HouseConfigConstants.SERVICE_LIST;
import static com.example.utils.HouseConfigConstants.STEP;
import static com.example.utils.HouseConfigConstants.UNITS;
import static com.example.utils.HouseConfigConstants.USER;
import static com.example.utils.HouseConfigConstants.USER_BLOCKED;
import static com.example.utils.HouseConfigConstants.USER_LIST;
import static com.example.utils.HouseConfigConstants.VALUE;
import static com.example.utils.HouseConfigConstants.VALUE_TYPE;

/**
 * Created by Serhii Razovyi on 10-Oct-19.
 */
public class DomoBusConfigLoader {

    private String _configFilesCommonPackage = "./commonlib/src/main/java/com/example/utils/configs";
    private String _configFilesServerPackage = "./server/src/main/java/com/example/server/configs";
    private HomeConfigEntity _homeConfig;

    /**
     * Instantiates a new Domo bus config loader.
     */
    public DomoBusConfigLoader(){}


    /**
     * Instantiates a new Domo bus config loader.
     *
     * @param configFileName the config file name
     * @param isFromServer   the is from server
     */
    public DomoBusConfigLoader(String configFileName, boolean isFromServer) {

        try {

            File directoryFiles = new File(isFromServer
                    ? _configFilesServerPackage
                    : _configFilesCommonPackage
            );

            File[] filesList = directoryFiles.listFiles();

            File file = null;

            if (filesList != null) {
                for (File f: filesList) {
                    if (configFileName.equalsIgnoreCase(f.getName())) {
                        file = f;
                    }
                }
                if (file != null){
                    SAXReader reader = new SAXReader();
                    Document document = reader.read(file);

                    createStructures(document);
                } else {
                    throw new DocumentException();
                }
            } else {
                throw new DocumentException();
            }
        }
        catch (DocumentException e) {
            System.out.println("There is no file with the name: " + configFileName);
            e.printStackTrace();
        }
    }

    /**
     * Instantiates a new Domo bus config loader.
     *
     * @param document the document
     */
    public DomoBusConfigLoader(Document document){
        createStructures(document);
    }

    /**
     * Get home config home config entity.
     *
     * @return the home config entity
     */
    public HomeConfigEntity getHomeConfig(){
        return _homeConfig;
    }

    private void createStructures(Document document){

        _homeConfig = new HomeConfigEntity();

        System.out.println("Root element :" + document.getRootElement().getName());

        Element classElement = document.getRootElement();


//        testingParser(classElement);

        parseHouse(classElement);
        parseFloors(classElement);
        parseDivisions(classElement);
        parseDivisionsType(classElement);
        parseUsersList(classElement);
        parseDevicesList(classElement);
        parseDeviceTypeList(classElement);
        parseServicesList(classElement);
        parseEnumValueTypeList(classElement);
        parseScalarValueTypeList(classElement);
    }


    private void parseFloors(Element classElement) {

        List<Node> floorListXML = classElement.selectNodes(HOUSE + "/" + FLOOR_LIST + "/" + FLOOR);
        List<Floor> floorList = new ArrayList<>();

        for (Node node : floorListXML) {
            Floor f = new Floor(
                    node.valueOf(getAttribute(ID)),
                    node.valueOf(getAttribute(NAME)),
                    node.valueOf(getAttribute(HEIGHT_ORDER))
            );
            floorList.add(f);
        }

        _homeConfig.setFloorList(floorList);
    }

    private void parseDivisions(Element classElement){

        List<Node> divisionListXML = classElement.selectNodes(HOUSE + "/" + DIVSION_LIST + "/" + DIVSION);
        List<Division> divisionList = new ArrayList<>();

        for (Node node : divisionListXML) {
            Division d = new Division(
                    node.valueOf(getAttribute(ID)),
                    node.valueOf(getAttribute(NAME)),
                    node.valueOf(getAttribute(REF_FLOOR)),
                    node.valueOf(getAttribute(ACCESS_LEVEL)),
                    node.valueOf(getAttribute(REF_DIVISION_TYPE))
            );
            divisionList.add(d);
        }

        _homeConfig.setDivisionList(divisionList);
    }

    private void parseDivisionsType(Element classElement){

        List<Node> divisionTypeListXML = classElement.selectNodes(DIVSION_TYPE_LIST + "/" + DIVSION_TYPE);
        List<DivisionType> divisionTypeList = new ArrayList<>();

        for (Node node : divisionTypeListXML) {
            DivisionType d = new DivisionType(
                    node.valueOf(getAttribute(ID)),
                    node.valueOf(getAttribute(NAME))
            );
            divisionTypeList.add(d);
        }

        _homeConfig.setDivisionTypeList(divisionTypeList);
    }

    private void parseHouse(Element classElement){

        Node houseXML = classElement.selectNodes(HOUSE).get(0);

        House h = new House(
                houseXML.valueOf(getAttribute(ID)),
                houseXML.valueOf(getAttribute(NAME)),
                houseXML.valueOf(getAttribute(ADDRESS)),
                houseXML.valueOf(getAttribute(PHONE)),
                null,
                null
        );

        _homeConfig.setHouse(h);
    }

    private void parseUsersList(Element classElement){

        List<Node> userListXML = classElement.selectNodes(USER_LIST + "/" + USER);
        List<User> userList = new ArrayList<>();

        for (Node node : userListXML) {
            User u = new User(
                    node.valueOf(getAttribute(ID)),
                    node.valueOf(getAttribute(NAME)),
                    node.valueOf(getAttribute(PASSWORD)),
                    node.valueOf(getAttribute(ACCESS_LEVEL))
            );
            userList.add(u);
        }

        _homeConfig.setUserList(userList);
    }

    private void parseServicesList(Element classElement){

        List<Node> serviceListXML = classElement.selectNodes(SERVICE_LIST + "/" + SERVICE);
        List<Service> serviceList = new ArrayList<>();

        for (Node node : serviceListXML) {
            Service s = new Service(
                    node.valueOf(getAttribute(ID)),
                    node.valueOf(getAttribute(NAME))
            );
            serviceList.add(s);
        }

        _homeConfig.setServiceList(serviceList);
    }

    private void parseDevicesList(Element classElement){

        List<Node> userListXML = classElement.selectNodes(DEVICE_LIST + "/" + DEVICE);
        List<Device> deviceList = new ArrayList<>();

        for (Node node : userListXML) {

            List<Node> deviceServiceListXML = node.selectNodes(DEVICE_LIST
                    + "/" + DEVICE
                    + "/" + DEVICE_SERVICE_LIST
                    + "/" + DEVICE_SERVICE
            );

            Device d = new Device(
                    node.valueOf(getAttribute(ID)),
                    node.valueOf(getAttribute(REF_DEVICE_TYPE)),
                    node.valueOf(getAttribute(NAME)),
                    node.valueOf(getAttribute(ADDRESS)),
                    node.valueOf(getAttribute(REF_DIVISION)),
                    node.valueOf(getAttribute(ACCESS_LEVEL)),
                    node.valueOf(getAttribute(USER_BLOCKED)),
                    parseInnerDeviceServiceList(deviceServiceListXML)
            );
            deviceList.add(d);
        }

        _homeConfig.setDeviceList(deviceList);
    }

    private List<DeviceService> parseInnerDeviceServiceList(List<Node> deviceServiceNodes){

        List<DeviceService> deviceList = new ArrayList<>();

        for (Node node : deviceServiceNodes) {
            DeviceService d = new DeviceService(
                    node.valueOf(getAttribute(REF_SERVICE))
            );
            deviceList.add(d);
        }
        return deviceList;
    }

    private void parseEnumValueTypeList(Element classElement){

        List<Node> enumValueTypeListXML = classElement.selectNodes(
                ENUM_VALUE_TYPE_LIST + "/" + ENUM_VALUE_TYPE);
        List<EnumValueType> enumValueTypeList = new ArrayList<>();

        for (Node node : enumValueTypeListXML) {

//            List<Node> enumeratedListXML = node.selectNodes(ENUM_VALUE_TYPE_LIST
//                    + "/" + ENUM_VALUE_TYPE
//                    + "/" + ENUMERATED
//            );
            List<Node> enumeratedListXML = node.selectNodes(
                    ENUMERATED
            );

            EnumValueType evt = new EnumValueType(
                    node.valueOf(getAttribute(NAME)),
                    node.valueOf(getAttribute(ID)),
                    parseInnerEnumeratedList(enumeratedListXML)
            );
            enumValueTypeList.add(evt);
        }

        _homeConfig.setEnumValueList(enumValueTypeList);
    }

    private List<Enumerated> parseInnerEnumeratedList(List<Node> enumeratedNodes){

        List<Enumerated> enumeratedList = new ArrayList<>();

        for (Node node : enumeratedNodes) {
            Enumerated e = new Enumerated(
                    node.valueOf(getAttribute(NAME)),
                    node.valueOf(getAttribute(VALUE))
            );
            enumeratedList.add(e);
        }
        return enumeratedList;
    }

    private void parseScalarValueTypeList(Element classElement){
        // Get EnumValueType Type List

        List<Node> scalarValueTypeListXML = classElement.selectNodes(
                SCALAR_VALUE_TYPE_LIST + "/" + SCALAR_VALUE_TYPE);
        List<ScalarValueType> scalarValueTypeList = new ArrayList<>();

        for (Node node : scalarValueTypeListXML) {

            ScalarValueType evt = new ScalarValueType(
                    node.valueOf(getAttribute(NAME)),
                    node.valueOf(getAttribute(STEP)),
                    node.valueOf(getAttribute(MIN_VALUE)),
                    node.valueOf(getAttribute(NUM_BITS)),
                    node.valueOf(getAttribute(UNITS)),
                    node.valueOf(getAttribute(ID)),
                    getValueConversion(),
                    node.valueOf(getAttribute(MAX_VALUE))
            );
            scalarValueTypeList.add(evt);
        }

        _homeConfig.setScalarValueList(scalarValueTypeList);
    }

    private ValueConversion getValueConversion(){
        // FIXME: 06-Nov-19 create value conversion
        return null;
    }

    private void parseDeviceTypeList(Element classElement){
        List<Node> deviceTypeListXML = classElement.selectNodes(
                DEVICE_TYPE_LIST + "/" + DEVICE_TYPE);
        List<DeviceType> deviceTypeList = new ArrayList<>();

        for (Node node : deviceTypeListXML) {

            List<Node> propertyListXML = node.selectNodes(
                    PROPERTY_LIST
                    + "/" + PROPERTY
            );

            DeviceType dt = new DeviceType(
                    node.valueOf(getAttribute(ID)),
                    node.valueOf(getAttribute(NAME)),
                    node.valueOf(getAttribute(DESCRIPTION)),
                    node.valueOf(getAttribute(REF_DEVICE_CLASS)),
                    parseInnerPropertyList(propertyListXML)
            );
            deviceTypeList.add(dt);
        }

        _homeConfig.setDeviceTypeList(deviceTypeList);
    }

    private List<Property> parseInnerPropertyList(List<Node> propertyNodes){

        List<Property> propertyList = new ArrayList<>();

        for (Node node : propertyNodes) {
            Property p = new Property(
                    node.valueOf(getAttribute(ID)),
                    node.valueOf(getAttribute(NAME)),
                    node.valueOf(getAttribute(ACCESS_MODE)),
                    node.valueOf(getAttribute(VALUE_TYPE)),
                    node.valueOf(getAttribute(REF_VALUE_TYPE))
            );
            propertyList.add(p);
        }
        return propertyList;
    }

    private void testingParser(Element classElement){

        List<Node> nodes = classElement.selectNodes(DEVICE_CLASS_LIST + "/" + DEVICE_CLASS);
        System.out.println("----------------------------\n");
        for (Node node : nodes) {
            System.out.println("Current Element : " + node.getName());
            System.out.println("ID : " + node.valueOf(getAttribute(ID)));
            System.out.println("Name : " + node.valueOf(getAttribute(NAME)));
        }
    }

    private String getAttribute(String attr){
        return "@" + attr;
    }

//    /**
//     * The entry point of application.
//     *
//     * @param args the input arguments
//     */
//    public static void main(String[] args) {
//
//        DomoBusConfigLoader configLoader = new DomoBusConfigLoader("basic_config_1.xml", false);
//        for (Division d : configLoader.getHomeConfig().getDivisionList()){
//            System.out.println(d.getName());
//        }
//    }
}
