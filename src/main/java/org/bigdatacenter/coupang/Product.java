package org.bigdatacenter.coupang;

public class Product {
    private Long productId;
    private Long itemId;
    private Long vendorItemId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getVendorItemId() {
        return vendorItemId;
    }

    public void setVendorItemId(Long vendorItemId) {
        this.vendorItemId = vendorItemId;
    }

    public String getProductDetailsPageURL() {
        return "http://www.coupang.com/vp/products/" + this.productId + "?itemId=" + this.itemId + "&vendorItemId=" + this.vendorItemId;
    }

    public String getProductDetailsSectionURL() {
        return "http://www.coupang.com/vp/products/" + this.productId + "/vendor-items/" + this.vendorItemId + "?isFixedVendorItem=true&type=sdp";
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", itemId=" + itemId +
                ", vendorItemId=" + vendorItemId +
                '}';
    }
}
