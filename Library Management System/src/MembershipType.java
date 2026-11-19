public enum MembershipType {
        Student (.25),
        Staff(0.01),
        Public(0.0);
        private final double waiver;
        MembershipType(double v) {
            this.waiver=v;
        }

        public double getWaiver() {
            return waiver;
        }
}
