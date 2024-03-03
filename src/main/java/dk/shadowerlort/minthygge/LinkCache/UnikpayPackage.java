package dk.shadowerlort.minthygge.LinkCache;

public class UnikpayPackage {
    private final String packageName;
    private final float amount;

    public UnikpayPackage(String packageName, float amount) {
        this.packageName = packageName;
        this.amount = amount;
    }

    public String getPackageName() {
        return packageName;
    }

    public float getAmount() {
        return amount;
    }
}
